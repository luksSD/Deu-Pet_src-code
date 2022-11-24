window.onload = function(){
      //Check File API support
      if(window.File && window.FileList && window.FileReader)
      {
          var filesInput = document.getElementById("files");


          filesInput.addEventListener("change", function(event){

              var files = event.target.files; //FileList object

              for(var i = 0; i< files.length; i++) {
                  var file = files[i];

                  var nameImg = file.name;

                  //Only pics
                  if(!file.type.match('image'))
                    continue;

                  var picReader = new FileReader();
                  picReader.addEventListener("load",function(event){

                      var picFile = event.target;

                      document.getElementById("imagem").src=picFile.result;

                      divResult.insertBefore(div,null);

                  });

                   //Read the image
                  picReader.readAsDataURL(file);
              }

          });
      }
      else
      {
          console.log("Your browser does not support File API");
      }

   }
