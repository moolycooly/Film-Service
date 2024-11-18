package org.fintech.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterCondition {
    private String field;
    private Object minValue;
    private Object maxValue;
    private List<Object> mustBe;
    private List<Object> mustNot;
    private List<Object> should;
    private Object match;

}
