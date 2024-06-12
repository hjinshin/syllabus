package knu.team7.syllabus.fetch.application.usecase;

public interface FetchUseCase {
    void fetchGE(int year, String season) throws Exception;

    void fetchOther(int year, String season) throws Exception;
}
