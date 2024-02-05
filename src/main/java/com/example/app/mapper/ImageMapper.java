package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Image;

@Mapper
@Transactional
public interface ImageMapper {

	//ユーザーの画像一覧取得
	List<Image> getImageByUserId(int id);
	
	//ページ分割
	//全体のデータ数
	Long count();
	//分割データ
	List<Image> selectLimited(@Param("offset") int offset,@Param("limit") int limit);

	//画像の個別取得
	Image getImageById(int id);

	//新規登録
	void add(Image image);

	//削除
	void delete(int id);

	//更新
	void edit(Image image);
}
