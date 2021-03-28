package com.lordgasmic.==PACKAGE_NAME==.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationErrorResponse {

    private List<Violation> violations = new ArrayList<>();
}
