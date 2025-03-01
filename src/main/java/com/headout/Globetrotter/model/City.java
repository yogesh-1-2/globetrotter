package com.headout.Globetrotter.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.mongodb.core.mapping.Document;

import com.headout.Globetrotter.model.base.BaseEntity;


@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "cities")
public class City extends BaseEntity {
    private String country;
}

