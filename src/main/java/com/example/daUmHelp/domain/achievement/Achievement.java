package com.example.daUmHelp.domain.achievement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "achievements")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Achievement {
}
