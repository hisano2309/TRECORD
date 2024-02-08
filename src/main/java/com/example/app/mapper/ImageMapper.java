package com.example.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Image;

@Mapper
@Transactional
public interface ImageMapper {

	// ユーザーの画像一覧取得
	List<Image> getImageByUserId(int id);

	// ページ分割
	// 全体のデータ数
	Long count();

	// 分割データ
	List<Image> selectLimited(@Param("offset") int offset, @Param("limit") int limit);

	// 画像の個別取得
//	Image getImageById(Map<String, Object> param);
//  個別でデータをとる記述
	List<Image> getImageById(@Param("id") Integer id, @Param("date") String date);

	// 新規登録
	void add(Image image);

	// 削除
//	int delete(Map<String, Object> param);
//  個別でデータをとる記述
	void delete(@Param("userId") Integer id, @Param("date") String date);

	// 更新
	List<Image> edit(Image image);
//	void edit(Map<String, Object> image);

}
