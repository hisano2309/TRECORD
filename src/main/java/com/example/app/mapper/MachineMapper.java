package com.example.app.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.Machine;
import com.example.app.domain.MachineSetCount;

@Mapper
public interface MachineMapper {

//表示//
	//筋トレマシン一覧表示
	List<Machine> selectMachine() throws Exception;

	//筋トレ記録全表示
//	List<MachineSetCount> selectAll() throws Exception;

	//カレンダーから特定の日の筋トレ記録表示
	List<MachineSetCount> selectDay(
			@Param("date") LocalDate date,
			@Param("userId") int userId) throws Exception;

	//Max値を取得
//	int selectMax(Integer machineId) throws Exception;

	//Min値を取得
//	int selectMin(Integer machineId) throws Exception;

	
//登録/
	//筋トレ記録の登録
	void insert(MachineSetCount MachineSetCount) throws Exception;

	
//編集//
	//筋トレ記録編集
//	void update(MachineSetCount machineRecord) throws Exception;

	
//削除//
	//筋トレ記録削除
//	void delete(LocalDateTime date) throws Exception;

}
