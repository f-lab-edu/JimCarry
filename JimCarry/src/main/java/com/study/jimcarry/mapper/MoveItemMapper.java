package com.study.jimcarry.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.study.jimcarry.domain.MoveItemEntity;

@Mapper
public interface MoveItemMapper {

	int insertMoveItem(MoveItemEntity moveItemEntity);
}
