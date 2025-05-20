package com.techzen.academy_n0325c1.dto.page;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    List<T> content;
    PageCustom<T> page;
    public PageResponse(Page<T> page) {
        this.content = page.getContent();
        this.page = new PageCustom<>(page);
    }
}
