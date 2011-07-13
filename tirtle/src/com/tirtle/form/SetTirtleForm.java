package com.tirtle.form;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class SetTirtleForm
{
    @NotNull
    @NumberFormat(style=Style.NUMBER)
    private Double value;
    
    public Double getValue() {
        return value;
    }
    
    public void setValue(Double value) {
        this.value = value;
    }
}