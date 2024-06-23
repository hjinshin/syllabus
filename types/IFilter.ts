export interface IFilter {
  name: string;
  value: string;
  enabled: boolean;
  essentiallyShow: boolean;
}

export const filters: IFilter[] = [
  {
    name: "전공",
    value: "major",
    enabled: true,
    essentiallyShow: true,
  },
  {
    name: "전공필수",
    value: "essential-major",
    enabled: true,
    essentiallyShow: true,
  },
  { name: "ABEEK", value: "abeek", enabled: true, essentiallyShow: false },
  {
    name: "교양",
    value: "general",
    enabled: false,
    essentiallyShow: true,
  },
  {
    name: "SDG 교양",
    value: "sdg",
    enabled: false,
    essentiallyShow: true,
  },
  {
    name: "조별과제",
    value: "team-project",
    enabled: false,
    essentiallyShow: false,
  },
];
