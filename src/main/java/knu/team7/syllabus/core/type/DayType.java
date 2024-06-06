package knu.team7.syllabus.core.type;

public enum DayType {
    Mon("월"),
    TUE("화"),
    WED("수"),
    THU("목"),
    FRI("금"),
    SAT("토"),
    SUN("일");

    private final String label;

    DayType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
