package ita.gptdemo.dto;

public class Gender {
    String gender;
    String name;
    int count;
    double probability;


    @Override
    public String toString() {
        return "Gender{" +
                "gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", probability=" + probability +
                '}';
    }
}
