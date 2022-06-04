package com.patni.lld.model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LMS {
    //    courseId, course Map
    Map<String, Course> courseMap = new HashMap<>();
    //    registrationId, CourseId map
    Map<String, RegistrationDetails> registrationMap = new TreeMap<>();

    public Map<String, Course> getCourseMap() {
        return courseMap;
    }


    public Map<String, RegistrationDetails> getRegistrationMap() {
        return registrationMap;
    }

}
