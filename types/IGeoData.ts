export interface IGeoData {
  buildingName: string;
  buildingCode: string;
  buildingLocation: {
    latitude: number;
    longitude: number;
  };
  buildingAddress: string;
  buildingFloor: number;
}

export const IT1: IGeoData = {
  buildingName: "IT1호관",
  buildingCode: "IT1",
  buildingAddress: "IT대학 1호관",
  buildingFloor: 7,
  buildingLocation: {
    latitude: 35.8875,
    longitude: 128.6129,
  },
};
export const IT5: IGeoData = {
  buildingName: "IT5호관",
  buildingCode: "IT5",
  buildingAddress: "IT대학 5호관",
  buildingFloor: 3,
  buildingLocation: {
    latitude: 35.5665,
    longitude: 126.978,
  },
};
