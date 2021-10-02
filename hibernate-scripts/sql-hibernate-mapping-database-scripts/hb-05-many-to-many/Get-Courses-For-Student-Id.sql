select courses0_.student_id as student_1_1_0_, courses0_.course_id as course_i2_1_0_, course1_.id as id1_0_1_, course1_.instructor_id as instruct3_0_1_, course1_.title as title2_0_1_, instructor2_.id as id1_2_2_, instructor2_.email as email2_2_2_, instructor2_.first_name as first_na3_2_2_, instructor2_.instructor_detail_id as instruct5_2_2_, instructor2_.last_name as last_nam4_2_2_, instructor3_.id as id1_3_3_, instructor3_.hobby as hobby2_3_3_, instructor3_.youtube_channel as youtube_3_3_3_ 
from course_student courses0_ 
inner join Course course1_ on courses0_.course_id=course1_.id 
	left outer join instructor instructor2_ on course1_.instructor_id=instructor2_.id 
	left outer join instructor_detail instructor3_ on instructor2_.instructor_detail_id=instructor3_.id 
where courses0_.student_id=2
