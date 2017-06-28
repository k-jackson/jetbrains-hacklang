## Jetbrains Hacklang Plugin
This plugin adds support for the Hack programming language to the Jetbrains IDEs.<br>
**Please note that it's a work in progress** at this point in time!

### Current Features:
- Syntax highlighting for main language features (excludes XHP, non-strict mode, and others)
- Parsing support for main language features (excludes XHP, non-strict mode, and others)
- In-place rename refactoring for local variables

### To Do List:
- Add Gradle support for easy installation
- Add support for XHP
- Add support for PHP mode and non-strict
- "FIXME" support
- Autocompletion features
- "Go to" features (implementations, declaration)
- Optimize grammar
- Much more

### Installation
- Follow the build environment setup instructions at <br> http://www.jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support/prerequisites.html <br> and http://www.jetbrains.org/intellij/sdk/docs/basics/getting_started/setting_up_environment.html
- Download the repository into the IDE project you created.
- Run GrammarKit's parser generator (Ctrl+Shift+G, or right-click menu entry) on the src/com.hack/Hack.bnf file 
to create and populate the /gen/ folder's classes.

### Using the Plugin
You can run the plugin from the IDE (Menu > Run > '\<YourProjectName\>') to open the plugin in a new IntelliJ instance, 
or you can generate the plugin's hack.jar under Menu > Buld > "Prepare plugin module '\<YourProjectName\>' for deployment". <br>
The jar can be moved into a /\<Jetbrains IDE\>/plugin/hack/lib/ folder to be distributed or used in IDEs other than IntelliJ.

The plugin won't be distributed via the plugin repo just yet, as many more features are still to be added.

### Contributing
Bug reports, feedback, and pull requests are always welcome.
