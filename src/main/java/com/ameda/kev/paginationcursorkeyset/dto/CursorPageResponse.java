package com.ameda.kev.paginationcursorkeyset.dto;

import java.util.List;

/**
 * Author: kev.Ameda
 */
public record CursorPageResponse<T>(
        List<T> data,
        int pageSize,
        String nextCursor,
        boolean hasNext
) {
}
