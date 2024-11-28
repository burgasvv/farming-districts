package org.burgas.farmingdistrictservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum LegalForm {

    LE("ЮР"), IE("ИП"), PE("ФЛ");

    private String name;

    LegalForm(String name) {
        this.name = name;
    }
}
