package com.example.app.service;

import java.util.Date;
import java.util.List;

import com.example.app.domain.Count;
import com.example.app.domain.Machine;
import com.example.app.domain.MachineSetCount;


public interface MachineSetCountService {
	
//確認//
	//本日分がすでに登録してあるか確認(グラフの日付重複対策)
//	List<MachineSetCount> checkDate(LocalDate date) throws Exception;

	// IDでweightDB、machineSetCountに記録があるか確認(データがある場合のみ表形式グラフの表示)
	List<MachineSetCount> checkId(int userId)throws Exception;

//登録//

	//筋トレ記録登録
	void addMachineSetCount(MachineSetCount machineRecord) throws Exception;

//表示//
	//筋トレマシン一覧表示
	List<Machine> getSelectMachine() throws Exception;

	//カレンダーから特定の日の筋トレ記録表示
	List<MachineSetCount> getMachineSetCountDay(String day, int userId) throws Exception;

	//マシン数をカウント
	int getCountMachine() throws Exception;

	//折れ線グラフ表示
	List<MachineSetCount> getSelectLineGraph(int userId, int machineId) throws Exception;

	//円グラフ表示
	List<Count> getSelectPieGraph(int UserId) throws Exception;

	// 前回のトレーニング重量表示
	MachineSetCount getSelectBefore(int userId, Integer machineId) throws Exception;

// 文字列からLocalDate型に変換
	Date convertToLocalDate(String date) throws Exception;



//編集//
	//筋トレ記録編集
//	void editMachineSetCount(MachineSetCount machineRecord) throws Exception;

//削除//
	//筋トレ記録削除
//	void deleteMachineSetCount(LocalDateTime date) throws Exception;
}
