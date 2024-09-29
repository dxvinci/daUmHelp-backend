package com.example.daUmHelp.domain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialTaskResponse {
    private Task task;
    private Boolean isSpecialTask;
}
