"use client";

import { openDrawerAtom, selectedCourseAtom } from "@/atoms";
import { Button } from "@/components/ui/button";
import {
  Drawer,
  DrawerClose,
  DrawerContent,
  DrawerDescription,
  DrawerFooter,
  DrawerHeader,
  DrawerTitle,
  DrawerTrigger,
} from "@/components/ui/drawer";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { useAtom } from "jotai";

const Info = ({ title, content }: { title: string; content: string }) => (
  <div className="flex flex-col">
    <p className="font-medium text-neutral-700">{title}</p>
    <p className="text-sm text-neutral-500">{content}</p>
  </div>
);

const Review = () => {
  return (
    <div className="flex flex-col">
      <p className="font-medium text-neutral-700">리뷰</p>
      <p className="text-sm text-neutral-500">리뷰가 없습니다.</p>
    </div>
  );
};

const DrawerContainer = () => {
  const [selectedCourse, setSelectedCourse] = useAtom(selectedCourseAtom);
  const [openDrawer, setOpenDrawer] = useAtom(openDrawerAtom);

  if (!selectedCourse) return null;

  return (
    <Drawer
      open={openDrawer}
      onOpenChange={(e) => {
        setOpenDrawer(e);
        if (!e) setSelectedCourse(null);
      }}
    >
      <DrawerContent>
        <div className="flex w-full flex-row justify-center ">
          <div className="w-full max-w-3xl space-y-8 py-6">
            <Tabs defaultValue="info" className="w-full">
              <TabsList className="w-full">
                <TabsTrigger className="w-1/2" value="info">
                  강의 정보
                </TabsTrigger>
                <TabsTrigger className="w-1/2" value="review">
                  리뷰
                </TabsTrigger>
              </TabsList>
              <TabsContent value="info" className="h-[60vh] w-full px-2">
                <DrawerHeader className="mx-0 px-0">
                  <DrawerTitle>{selectedCourse?.sbjctNm}</DrawerTitle>
                </DrawerHeader>
                <div className="grid grid-cols-2 gap-6">
                  <Info title="교수" content={selectedCourse?.professor} />
                  <Info
                    title="학점"
                    content={selectedCourse?.credit.toString() + "학점"}
                  />
                  <Info
                    title="강의실"
                    content={selectedCourse?.building + selectedCourse?.room}
                  />
                  <Info title="시간" content={selectedCourse?.realLecTime} />
                  <Info
                    title="수강대상"
                    content={selectedCourse?.grade + "학년"}
                  />
                </div>
              </TabsContent>
              <TabsContent value="review" className="h-[60vh] w-full px-2">
                <Review />
              </TabsContent>
            </Tabs>
            <div className="flex w-full flex-row justify-center">
              <DrawerClose asChild>
                <Button className="w-full" variant="outline">
                  닫기
                </Button>
              </DrawerClose>
            </div>
          </div>
        </div>
      </DrawerContent>
    </Drawer>
  );
};

export default DrawerContainer;
