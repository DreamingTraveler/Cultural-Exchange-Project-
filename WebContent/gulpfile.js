var gulp = require('gulp');
var concat = require('gulp-concat');
var minifyCSS = require('gulp-minify-css');
var uglify = require('gulp-uglify');
var livereload = require('gulp-livereload');
var rename = require('gulp-rename');

watch = require('gulp-watch');

gulp.task('html', function(){
  return gulp.src('pages/*.html');
});

gulp.task('jsp', function(){
  return gulp.src('*.jsp');
});

gulp.task('concat', function(){
   return gulp.src('src/css/*.css')
     .pipe(concat('style.css'))
     .pipe(gulp.dest('dist/css/'));
});

gulp.task('css', function(){
   return gulp.src('dist/css/style.css')
     .pipe(livereload());
});

gulp.task('minify-css',['concat'], function(){
   return gulp.src('dist/css/style.css')
     .pipe(minifyCSS({
        keepBraks: true,
     }))
     .pipe(rename(function(path){
       path.basename += ".min";
       path.extname = ".css";
     }))
     .pipe(gulp.dest('dist/css/'));
});

gulp.task('uglify', function(){
    return gulp.src('src/js/*.js')
      .pipe(concat('../../dist/js/script.min.js'))
      .pipe(uglify())
      .pipe(gulp.dest('dist/js/'));
});

gulp.task('watch', function(){
   livereload.listen();
   watch('pages/*.html', function(){
	 gulp.start('html');   
   });
   
   watch('*.jsp', function(){
     gulp.start('jsp');
   });

   watch('src/css/*.css', function(){
     gulp.start('concat');
     gulp.start('minify-css');
   });

   watch('dist/css/style.css', function(){
     gulp.start('css');
   });

   watch('src/js/*.js', function(){
     gulp.start('uglify');
   });
});
