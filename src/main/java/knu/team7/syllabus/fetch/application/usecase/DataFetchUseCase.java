package knu.team7.syllabus.fetch.application.usecase;

public interface DataFetchUseCase {
    void fetchData(int year, String season) throws Exception;
    void fetchGEData(int year, String season) throws Exception;
    void fetchOtherData(int year, String season) throws Exception;
}
