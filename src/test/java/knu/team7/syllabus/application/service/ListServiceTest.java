package knu.team7.syllabus.application.service;

import knu.team7.syllabus.application.port.in.command.CodeCommand;
import knu.team7.syllabus.application.usecase.ListUseCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListServiceTest {

    @Test
    public void testGetGEList() {
        try {
            ListUseCase listUseCase = new ListService();
            List<CodeCommand> list = listUseCase.getGEList();
            System.out.println(list);
            assertNotEquals(list.size(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetSubjectList() {
        try {
            ListUseCase listUseCase = new ListService();
            List<CodeCommand> list = listUseCase.getSubjectList();
            System.out.println(list);
            assertNotEquals(list.size(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}