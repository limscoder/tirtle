package com.tirtle.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateTirtleForm
{
    @NotNull
    @Size(min=1, max=250)
    private String label;
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
}