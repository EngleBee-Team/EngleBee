package com.beelinkers.englebee.general.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponseDTO {
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
}
