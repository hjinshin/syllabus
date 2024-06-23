"use client";

import DataTable from "./data-table";
import { columns } from "./columns";
import { showMapPopupAtom } from "@/atoms/map-popup-atoms";
import { useAtom } from "jotai";
import axios from "axios";
import { motion } from "framer-motion";
import { ICardData } from "@/types/ICardData";
import { Suspense, useEffect } from "react";
import {
  lectureListAtom,
  searchQueryAtom,
  searchQueryInputAtom,
} from "@/atoms/finder";
import { filters } from "@/types/IFilter";
import { ChevronDown } from "lucide-react";
import { selectedFiltersAtom, viewAllFiltersAtom } from "@/atoms/finder";
import { Badge } from "@/components/ui/badge";
import { ILectureRequest } from "@/types/api/ILectureRequest";
import { Input } from "@/components/ui/input";
import { Toast, ToastProvider } from "@/components/ui/toast";
import { TooltipProvider } from "@/components/ui/tooltip";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { Label } from "@/components/ui/label";
import { SearchQuery } from "@/types/etcTypes";
import { Button } from "@/components/ui/button";
import { ILectureResponse } from "@/types/api/ILectureResponse";

interface Props extends ICardData {
  isSelected: boolean;
  history: {
    push: (route: string) => void;
  };
}

const ViewAllFiltersToggle = () => {
  const [viewAllFilters, setViewAllFilters] = useAtom(viewAllFiltersAtom);
  return (
    <Badge
      variant="outline"
      className="group flex cursor-pointer select-none flex-row items-center border-neutral-400 duration-200 hover:bg-neutral-100"
      onClick={() => setViewAllFilters(!viewAllFilters)}
    >
      <ChevronDown
        className={`-ml-1 h-4 w-4 stroke-neutral-400  ${viewAllFilters === true ? `rotate-180` : ``}`}
      />
      <p className="text-nowrap">
        {viewAllFilters ? `간단히 보기` : `모든 필터 보기`}
      </p>
    </Badge>
  );
};

const Filters = () => {
  const [showAllFilters, setShowAllFilters] = useAtom(viewAllFiltersAtom);
  const [filters, setFilters] = useAtom(selectedFiltersAtom);

  return filters.map((badge, index) => {
    if (showAllFilters === false && badge.essentiallyShow === false)
      return null;
    return (
      <Badge
        key={index}
        // style={{
        //   backgroundColor: badge.color,
        // }}
        className="cursor-pointer select-none text-nowrap"
        variant={badge.enabled ? "outline" : undefined}
        onClick={() =>
          setFilters((draft) => {
            draft[index].enabled = !draft[index].enabled;
          })
        }
      >
        {badge.name}
      </Badge>
    );
  });
};

const FiltersContainer = () => {
  return (
    <div className="flex flex-col items-start rounded-md bg-neutral-50 p-4">
      <p className="px-2 py-1 text-base font-semibold text-neutral-600">필터</p>
      {/* <p className="px-2 py-0.5 text-xs font-medium text-neutral-500">
        <span className="cursor-pointer rounded-md bg-neutral-200 px-[3px] py-0.5 font-semibold hover:bg-neutral-300">
          개인 설정
        </span>
        에서 학과를 선택하면 필터가 맞춤설정됩니다.
      </p> */}
      <div className="flex w-full  gap-2 p-2">
        <Filters />
        <ViewAllFiltersToggle />
      </div>
    </div>
  );
};

const Title = () => (
  <h1 className="text-xl font-semibold text-neutral-700">강의 찾기</h1>
);

const SearchBar = () => {
  const [searchQueryInput, setSearchQueryInput] = useAtom(searchQueryInputAtom);
  const [searchQuery, setSearchQuery] = useAtom(searchQueryAtom);
  const [lectureList, setLectureList] = useAtom(lectureListAtom);
  const [selectedFilters, setSelectedFilters] = useAtom(selectedFiltersAtom);

  useEffect(() => {
    setSearchQuery("courseName");
  }, []);

  const search = async (input: string) => {
    
    let injectedValue;
    let filter = selectedFilters.find((filter) => filter.enabled === true);

    console.log(filter);
    console.log(selectedFilters)
    switch (searchQuery) {
      case "courseName":
        injectedValue = { sbjctNm: input };
        break;
      case "professor":
        injectedValue = { professor: input };
        break;
      case "code":
        injectedValue = { crseNo: input };
        break;
      default:
        injectedValue = { sbjctNm: input };
        break;
    }


    console.log(`injectedValue: ${injectedValue}`);
    console.log(injectedValue);

    const body: ILectureRequest = {
      year: 2024,
      season: "1학기",
      evaluation: {
        attendance: -1,
        midExam: -1,
        finalExam: -1,
        assignment: -1,
        presentation: -1,
        debate: -1,
        safetyEdu: -1,
        etc: -1,
        total: -1,
      },
      lang: "한국어",
      ...injectedValue,
    } as unknown as ILectureRequest;
    try {
      const response = await axios.post("/api/v1/lecture", body, {
        withCredentials: true,
      });
      console.log(response.data);
      const lectureResponse = response.data as ILectureResponse;
      if (lectureResponse.data.length === 0) {
        console.log("no data");
        return;
      }
      setLectureList(lectureResponse.data);
    } catch (e) {
      console.error(e);
    }
  };

  return (
    <div className="flex w-full flex-col items-start space-y-2 rounded-lg bg-neutral-50 p-4">
      <p className="text-base font-semibold text-neutral-600">검색어</p>
      <div className="w-30 flex flex-row items-center space-x-2">
        <Label className="text-nowrap">검색 범주</Label>
        <Button
          className="h-7 p-2 text-xs"
          variant={searchQuery === "courseName" ? "default" : "secondary"}
          onClick={() => {
            setSearchQuery("courseName");
          }}
        >
          교과목명
        </Button>
        <Button
          className="h-7 p-2 text-xs"
          variant={searchQuery === "professor" ? "default" : "secondary"}
          onClick={() => {
            setSearchQuery("professor");
          }}
        >
          담당교수
        </Button>
        <Button
          className="h-7 p-2 text-xs"
          variant={searchQuery === "code" ? "default" : "secondary"}
          onClick={() => {
            setSearchQuery("code");
          }}
        >
          교과목코드
        </Button>
        {/* <Select
          onValueChange={(value) => {
            console.log(value);
            setSearchQuery(value as SearchQuery);
          }}
        >
          <SelectTrigger id="search-query-selector">
            <SelectValue>
              {(() => {
                switch (searchQuery) {
                  case "courseName":
                    return "교과목명";
                  case "professor":
                    return "담당교수";
                  case "code":
                    return "교과목코드";
                  default:
                    return "교과목명";
                }
              })()}
            </SelectValue>
          </SelectTrigger>
          <SelectContent >
            <SelectItem value="code">교과목코드</SelectItem>
            <SelectItem value="courseName">교과목명</SelectItem>
            <SelectItem value="professor">담당교수</SelectItem>
          </SelectContent>
        </Select> */}
      </div>
      <div className="flex w-full flex-row">
        <Input
          className="w-full"
          placeholder="검색어 입력..."
          onInput={(e) => {
            const input = e.currentTarget.value;
            setSearchQueryInput(input);
            if (input.length > 1) search(input);
          }}
        />
      </div>
      {/* <p className="text-xs font-medium text-neutral-500">
        <strong>교과목코드, 교과목명, 담당교수</strong>로 검색하세요
      </p> */}
    </div>
  );
};

// const Helper = () => {
//   return (
//     <ToastProvider>
//       <Toast>asdf</Toast>
//     </ToastProvider>
//   );
// };

const Indexer = () => {
  return (
    <TooltipProvider>
      {/* <div className="flex cursor-pointer select-none flex-col items-start space-y-4 rounded-lg bg-neutral-100 px-3 py-2"> */}
      <div className="flex flex-row items-baseline space-x-1">
        <p className="text-lg font-semibold text-neutral-700">2024</p>
        <p className="text-sm font-semibold text-neutral-500">1학기</p>
      </div>
      {/* </div> */}
    </TooltipProvider>
  );
};

const DataTableContainer = () => {
  const [lectureList, setLectureList] = useAtom(lectureListAtom);

  return (
    <div className="flex h-full w-1/2 rounded-lg">
      {lectureList ? (
        <DataTable columns={columns} data={lectureList} />
      ) : (
        <div className="flex h-1/2 w-full flex-col items-center justify-center">
          <p className="text-sm font-medium text-neutral-500">
            아직 검색을 시작하지 않았습니다
          </p>
        </div>
      )}
    </div>
  );
};

const IndexerContainer = () => {
  const [lectureList, setLectureList] = useAtom(lectureListAtom);

  console.log(lectureList);

  // useEffect(() => {
  //   (async () => {
  //     // const response = await fetch("/api/v1/lecture", {
  //     //   method: "POST",
  //     //   credentials: "include",
  //     //   headers:{
  //     //     "Content-Type": "application/json",
  //     //   },
  //     //   body: JSON.stringify({
  //     //     type: "lecture",
  //     //   }),
  //     // });
  //     const body: ILectureRequest = {
  //       year: 2024,
  //       season: "1학기",
  //       crseNo: "ELEC0243-001",
  //       sbjctNm: "회로이론1",
  //     };
  //     const response = await axios.post("/api/v1/lecture", body, {
  //       withCredentials: true,
  //     });
  //     // if (!response.ok) {
  //     //   console.error(response.statusText);
  //     //   return new Response(response.statusText, { status: response.status });
  //     // }
  //     // const data = await response.json();
  //     // console.log(data);
  //   })();
  // }, []);

  return (
    <div
      className={`relative flex h-full w-1/2 flex-row items-start space-x-12`}
    >
      <div className="flex flex-col items-start space-y-2">
        <Indexer />
        <FiltersContainer />
        <SearchBar />
      </div>
    </div>
  );
};

const Finder = () => {
  const [showMapPopup, setShowMapPopup] = useAtom(showMapPopupAtom);

  return (
    <main className="container h-full w-full max-w-5xl flex-col space-y-2 px-8 py-5">
      <Title />
      <div className="flex h-full w-full flex-row items-start space-x-12">
        <IndexerContainer />
        <DataTableContainer />
      </div>
    </main>
  );
};

export default Finder;
