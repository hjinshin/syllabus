package knu.team7.syllabus.application.usecase;

public interface DataFetchUseCase {
    void fetchGEData(int year, String season) throws Exception;
    void fetchOtherData(int year, String season) throws Exception;
}
