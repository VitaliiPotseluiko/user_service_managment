package com.vitalii.userservicemanagment.mapper;

public interface DtoMapper<R, M, D> {
    M toModel(R requestDto);

    D toDto(M model);
}
