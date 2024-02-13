package com.example.app.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.Count;
import com.example.app.domain.Machine;
import com.example.app.domain.MachineSetCount;

@Mapper
public interface MachineMapper {

	//登録/
	//筋トレ記録の登録
	void insert(MachineSetCount MachineSetCount) throws Exception;

//表示//
	//筋トレマシン一覧表示
	List<Machine> selectMachine() throws Exception;

	//カレンダーから特定の日の筋トレ記録表示
	List<MachineSetCount> selectDay(
			@Param("date") LocalDate date,
			@Param("userId") int userId) throws Exception;
	
	//マシン数をカウント
	int countMachine() throws Exception;
	
	//折れ線グラフ表示
	List<MachineSetCount> selectLineGraph(
			@Param("userId")int userId, 
			@Param("machineId") int machineId) throws Exception;
	
	//円グラフ表示
	List<Count> selectPieGraph(int UserId) throws Exception;
	

	
//編集//
	//筋トレ記録編集
//	void update(MachineSetCount machineRecord) throws Exception;

	
//削除//
	//筋トレ記録削除
//	void delete(LocalDateTime date) throws Exception;

}
