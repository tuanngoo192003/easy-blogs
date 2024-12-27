package com.project.core.viewmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponse<T extends Serializable> implements Serializable {
    private int page;
    private int perPage;
    private int totalPage;
    private Long totalRecord;
    private List<T> data;
}
