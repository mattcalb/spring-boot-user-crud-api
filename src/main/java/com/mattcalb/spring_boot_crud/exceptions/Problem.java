package com.mattcalb.spring_boot_crud.exceptions;

import java.util.List;

public record Problem(Integer status,
                      String type,
                      String title,
                      String detail,
                      List<String> errors
) {
    public Problem(Integer status, String type, String title, String detail) {
        this(status, type, title, detail, null);
    }

}
