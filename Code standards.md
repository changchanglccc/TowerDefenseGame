# Code Standards Statement - Google Java Style

We are basing our code standards off Google' code standard at https://google.github.io/styleguide/javaguide.html
There are slight changes but the majority of items are copied as is. That's why we are keeping the reference numbers

##2.1 File name 

The source file name consists of the case-sensitive name of the top-level class it contains, plus the .java extension.

##4.1.1 Braces are used where optional 

Braces are used with if, else, for, do and while statements, even when the body is empty or contains only a single statement.

##4.1.2 Nonempty blocks: K & R style 

Braces follow the Kernighan and Ritchie style ("Egyptian brackets") for nonempty blocks and block-like constructs:

No line break before the opening brace.
Line break after the opening brace.
Line break before the closing brace.
Line break after the closing brace if that brace terminates a statement or the body of a method, constructor or named class. For example, there is no line break after the brace if it is followed by else or a comma.

##4.2 Block indentation: +4 spaces (originally 2)

Each time a new block or block-like construct is opened, the indent increases by two spaces. When the block ends, the indent returns to the previous indent level. The indent level applies to both code and comments throughout the block. (See the example in Section 4.1.2, Nonempty blocks: K & R Style.)

##4.4 Column limit: 100 or 130 

Projects are free to choose a column limit of either 80 or 100 characters. Except as noted below, any line that would exceed this limit must be line-wrapped, as explained in Section 4.5, Line-wrapping.

##4.6.2 Horizontal whitespace 

Beyond where required by the language or other style rules, and apart from literals, comments and Javadoc, a single ASCII space also appears in the following places only.

* Separating any reserved word, such as if, for or catch, from an open parenthesis (() that follows it on that line
* Separating any reserved word, such as else or catch, from a closing curly brace (}) that precedes it on that line

##4.8.1 Enum classes 

After each comma that follows an enum constant, a line-break is optional.

An enum class with no methods and no documentation on its constants may optionally be formatted as if it were an array initializer (see Section 4.8.3.1 on array initializers).

Since enum classes are classes, all other rules for formatting classes apply.

##4.8.2.1 One variable per declaration 

Every variable declaration (field or local) declares only one variable: declarations such as int a, b; are not used.

##4.8.5 Annotations 

Annotations applying to a class, method or constructor appear immediately after the documentation block, and each annotation is listed on a line of its own (that is, one annotation per line). These line breaks do not constitute line-wrapping (Section 4.5, Line-wrapping), so the indentation level is not increased. 

##5.2.2 Class names 

Class names are written in UpperCamelCase.

Class names are typically nouns or noun phrases. For example, Character or ImmutableList. Interface names may also be nouns or noun phrases (for example, List), but may sometimes be adjectives or adjective phrases instead (for example, Readable).

There are no specific rules or even well-established conventions for naming annotation types.

Test classes are named starting with the name of the class they are testing, and ending with Test. For example, HashTest or HashIntegrationTest.

##5.2.3 Method names 

Method names are written in lowerCamelCase.

Method names are typically verbs or verb phrases. For example, sendMessage or stop.

Underscores may appear in JUnit test method names to separate logical components of the name. One typical pattern is test<MethodUnderTest>_<state>, for example testPop_emptyStack. There is no One Correct Way to name test methods.

##5.2.6 Parameter names 

Parameter names are written in lowerCamelCase.

One-character parameter names should be avoided.

##5.2.7 Local variable names 

Local variable names are written in lowerCamelCase, and can be abbreviated more liberally than other types of names.

However, one-character names should be avoided, except for temporary and looping variables.

Even when final and immutable, local variables are not considered to be constants, and should not be styled as constants.
