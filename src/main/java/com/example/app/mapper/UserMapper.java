package com.example.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.User;

@Mapper
public interface UserMapper {
	
	
	//ユーザー登録
	void insert(User user);
	
	//ログイン認証
	User findByLoginId(String loginId);
	
	
	//ユーザー情報個別取得
	User selectById(Integer id);
	
	//削除
	void delete(Integer id);
	
	//更新
	void update(User user);

	

}
