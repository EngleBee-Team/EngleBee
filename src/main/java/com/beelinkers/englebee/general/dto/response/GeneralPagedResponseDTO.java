package com.beelinkers.englebee.general.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GeneralPagedResponseDTO<T> {
    private List<T> content;
    private PaginationResponseDTO pagination;
}
