package com.fresh.restapi.aspect;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface NeedToken {
}
