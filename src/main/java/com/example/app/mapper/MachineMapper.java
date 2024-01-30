package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Machine;

@Mapper
public interface MachineMapper {
	
//表示//
	//筋トレマシン一覧表示
	List<Machine> selectAll() throws Exception;

//登録//
	//筋トレ記録登録
	void insert(Machine machineRecord) throws Exception;

	
//編集//
	//筋トレ記録編集
	void update(Machine machineRecord) throws Exception;
	
//削除//
	//筋トレ記録削除
	void delete(Machine machineRecord) throws Exception;

}
