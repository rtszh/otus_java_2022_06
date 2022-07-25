package homework;


import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestProcessor<T> {

    private final Set<Class> testAnnotations = Set.of(
            Before.class,
            After.class,
            Test.class
    );

    private final Method[] methods;
    private final Constructor<T> constructor;

    public TestProcessor(Class<T> clazz) throws Exception {
        this.methods = clazz.getDeclaredMethods();
        this.constructor = clazz.getConstructor();
    }

    public void startTest() {

        // Для каждого метода определяем перечень всех его аннотаций
        final Map<Method, Annotation[]> methodMap = Arrays.stream(methods)
                .collect(Collectors.toMap(
                                Function.identity(),
                                Method::getAnnotations
                        )
                );

        // Оставляем только аннотации из testAnnotations
        final Map<Method, List<Annotation>> filteredMethodMap = new HashMap<>();
        methodMap.forEach((key, value) -> {

            List<Annotation> filteredAnnotations = Arrays.stream(value)
                    .filter(annotation -> testAnnotations.contains(annotation.annotationType()))
                    .collect(Collectors.toList());

            filteredMethodMap.put(key, filteredAnnotations);
        });

        // Формируем тройки before-test-after
        final List<List<Method>> testList = buildTestList(filteredMethodMap);

        // Запускаем тесты
        final Statistics statistics = new Statistics();

        testList
                .forEach(testMethods -> {
                    var instance = createNewInstance();
                    var testFlags = new TestFlags();
                    testMethods
                            .forEach(method -> {
                                if (checkBeforeAnnotation(method)) {
                                    try {
                                        method.invoke(instance);
                                    } catch (Exception e) {
                                        System.out.println(method.getName() + ": @Before method failed");
                                        statistics.incrementFailedTests();
                                        testFlags.setBeforeFlagFalse();
                                        testFlags.setTestFlagFalse();
                                    }
                                } else if (testFlags.getBeforeFlag() && checkTestAnnotation(method)) {
                                    try {
                                        System.out.println(method.getName() + ": Starting test");
                                        method.invoke(instance);
                                        System.out.println(method.getName() + ": Test passed");
                                    } catch (Exception e) {
                                        System.out.println(method.getName() + ": Test failed");
                                        statistics.incrementFailedTests();
                                        testFlags.setTestFlagFalse();
                                    }
                                } else if (checkAfterAnnotation(method)) {
                                    try {
                                        method.invoke(instance);
                                        if (testFlags.getTestFlag()) {
                                            statistics.incrementPassedTests();
                                        }
                                    } catch (Exception e) {
                                        System.out.println(method.getName() + ": @After method failed");
                                        if (testFlags.getTestFlag()) {
                                            statistics.incrementFailedTests();
                                        }
                                    }
                                }
                            });
                });

        // Вывод статистики по тестам
        System.out.println("Total tests: " + statistics.getSumTests());
        System.out.println("Tests passed: " + statistics.getPassedTests());
        System.out.println("Tests failed: " + statistics.getFailedTests());
    }

    private boolean checkTestAnnotation(Method method) {
        final var annotationTypeList = Arrays.stream(method.getAnnotations())
                .map(Annotation::annotationType)
                .collect(Collectors.toSet());

        return annotationTypeList.contains(Test.class);

    }

    private boolean checkBeforeAnnotation(Method method) {
        final var annotationTypeList = Arrays.stream(method.getAnnotations())
                .map(Annotation::annotationType)
                .collect(Collectors.toSet());

        return annotationTypeList.contains(Before.class);

    }

    private boolean checkAfterAnnotation(Method method) {
        final var annotationTypeList = Arrays.stream(method.getAnnotations())
                .map(Annotation::annotationType)
                .collect(Collectors.toSet());

        return annotationTypeList.contains(After.class);

    }

    private T createNewInstance() {
        T result = null;
        try {
            result = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<List<Method>> buildTestList(Map<Method, List<Annotation>> filteredMethodMap) {

        // Заполняем списки методов, у которых есть аннотация Before, After и Test
        List<Method> listMethodsWithBeforeAnnotation = new ArrayList<>();
        List<Method> listMethodsWithAfterAnnotation = new ArrayList<>();
        List<Method> listMethodsWithTestAnnotation = new ArrayList<>();
        filteredMethodMap.forEach((key, value) -> {
            var annotationTypeList = value.stream()
                    .map(Annotation::annotationType)
                    .collect(Collectors.toList());

            if (annotationTypeList.contains(Before.class)) {
                listMethodsWithBeforeAnnotation.add(key);
            }

            if (annotationTypeList.contains(After.class)) {
                listMethodsWithAfterAnnotation.add(key);
            }

            if (annotationTypeList.contains(Test.class)) {
                listMethodsWithTestAnnotation.add(key);
            }
        });

        return listMethodsWithTestAnnotation.stream()
                .map(method -> {
                    List<Method> resultList = new ArrayList<>();
                    resultList.addAll(listMethodsWithBeforeAnnotation);
                    resultList.add(method);
                    resultList.addAll(listMethodsWithAfterAnnotation);
                    return resultList;
                })
                .collect(Collectors.toList());
    }
}
