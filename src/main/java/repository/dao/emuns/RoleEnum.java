package repository.dao.emuns;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum RoleEnum {
    ROLE_USER ("User"),
    ROLE_TUTOR ("Tutor"),
    ROLE_ADMIN ("Admin");

    private final String name;

    RoleEnum(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static List<String> getAllRules(){
        return Arrays.stream(RoleEnum.values()).map(RoleEnum::getName).collect(Collectors.toList());
    }

}
