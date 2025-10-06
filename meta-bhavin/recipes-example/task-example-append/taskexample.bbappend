

do_configure:append(){
    echo "#define HELLO y" >> ${S}/cplusplus/meta-bhavin-sample-code/taskexample.h
    echo "#define BYE y" >> ${S}/cplusplus/meta-bhavin-sample-code/taskexample.h
}