package com.org.ems_service.util;

public class QueryConstant {

	public static String INSERT_TEMP_QUESTION = "INSERT INTO temp_questions(question,option_a,option_b,option_c,option_d,option_e,option_f,correct_option,option_type_id,question_level_id,chapter_id,is_active,inserted_by,updated_by) "
			+ "VALUES(:QUESTION,:OPTION_A,:OPTION_B,:OPTION_C,:OPTION_D,:OPTION_E,:OPTION_F,:CORRECT_OPTION,:OPTION_TYPE_ID,:QUESTION_LEVEL_ID,:CHAPTER_ID,:IS_ACTIVE,:INSERTED_BY,:UPDATED_BY)";

	public static String FIND_QUESTION_BY_ID = "SELECT id,question,option_a,option_b,option_c,option_d,option_e,option_f,correct_option,option_type_id,question_level_id,chapter_id,is_active,inserted_by,updated_by FROM temp_questions WHERE id = :ID";
}
