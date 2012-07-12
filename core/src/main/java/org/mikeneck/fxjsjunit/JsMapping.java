package org.mikeneck.fxjsjunit;

/**
 * meta information for retrieve the object from JavaScript Object.
 * <br/>
 *
 * JavaScript object is like a object that has key - value pairs.
 * To convert object from JavaScript form <i>i.e. {@code netscape.javascript.JSObject}</i>
 * to Plain Old Java Object a {@code getter} function will be used in given {@code order}.
 * <br/>
 *
 * If {@code getter} and {@code order} is not given, values are retrieved by field name and
 * natural order which is depend on each Java implement.
 * <br/>
 *
 * <span style="font-weight : bold">Example</span>
 *
 * Plain Old Java Object
 * <pre><code>
 *     public class Person {
 *         &#64JsMapping
 *         private String firstName;
 *         &#64JsMapping
 *         private String lastName;
 *         &#64JsMapping (getter = "getAndAddAge", order = 1)
 *         private int previousAge;
 *         &#64JsMapping (getter = "addAndGetAge", order = 2)
 *         private int nextYearsAge;
 *     }
 * </code></pre>
 * <br/>
 * expected JavaScript Object
 * <pre><code>
 *     {
 *         firstName : "Charles",
 *         lastName : "Dickens",
 *         age : 58,
 *         getAndAddAge : function () {
 *             var temporary = this.age;
 *             this.age++;
 *             return temporary;
 *         },
 *         addAndGetAge : function () {
 *             return ++this.age;
 *         }
 *     }
 * </code></pre>
 * <br/>
 *
 * In this case you can call {@link JsJUnit#callAs(String, Class)} like...
 * <pre><code>
 *     Person person = jsJUnit ("someFunction ()", Person.class);
 * </code></pre>
 *
 * And then the test bellow will pass.
 * <pre><code>
 *     assertThat(person.getPreviousAge(), is(58));
 *     assertThat(person.getNextYearsAge(), is(60));
 * </code></pre>
 * <br/>
 *
 * Because the order of calling getter function is (1) {@code getAndAddAge} and (2)
 * {@code addAndGetAge}, the function {@code getAndAddAge} is called first and returns 58
 * and add an age so that the value of age will become 59, which will give 60 when the
 * function {@code addAndGetAge} is called.
 *
 * @author mike_neck
 */
public @interface JsMapping {

    public String getter () default "";

    public int order () default 0;
}
