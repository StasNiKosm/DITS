package repository.dao.emuns;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public enum RoleEnum {
    User("ROLE_USER"),
    Tutor("ROLE_TUTOR"),
    Admin("ROLE_ADMIN");

    private final String name;

    RoleEnum(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static List<String> getAllRules(){
        return Arrays.stream(RoleEnum.values()).map(Objects::toString).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(RoleEnum.User);
    }

}
