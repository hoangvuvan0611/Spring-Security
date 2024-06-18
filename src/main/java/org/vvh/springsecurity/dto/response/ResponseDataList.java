package org.vvh.springsecurity.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseDataList<T> {
    int total;
    List<T> data;

    public ResponseDataList(List<T> data) {
        this.data = data;
        this.total = data.size();
    }
}
