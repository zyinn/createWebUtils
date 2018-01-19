<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.finruntech.frt.fits.pledge.${className?cap_first}Mapper">
    <insert id="${className}Save" parameterType="com.finruntech.frt.fits.pledge.${className?cap_first}Entity">
        INSERT INTO ${tableName} (
            <#list columnDesc?keys as key>
                ${key},
            </#list>
        )VALUES (
            <#list columnMap?keys as key>
                ${key},
            </#list>
        )
    </insert>
</mapper>
