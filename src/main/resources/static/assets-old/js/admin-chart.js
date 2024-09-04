$(function(){

  function userData() {
    $.ajax({
      url: '/api/admin/member-chart',
      method: 'GET',
      contentType: "application/json",
      success: function (data) {

        const grammarList = data.grammarList;
        const sentenceList = data.sentenceList;
        const wordList = data.wordList;
        const ageList = data.ageGroupList;

        console.log("성공");

        ageList.forEach(function (item) {
          console.log(item.studentGrade + ": " + item.count);
        });

        // levelCode와 count를 별도의 배열로 분리
        const grammerLabels = grammarList.map(item => item.levelCode);  // 레벨 코드들
        const grammerCounts = grammarList.map(item => item.count);  // 각 레벨에 해당하는 카운트

        const sentenceLabels = sentenceList.map(item => item.levelCode);
        const sentenceCounts = sentenceList.map(item => item.count);

        const wordLabels = wordList.map(item => item.levelCode);
        const wordCounts = wordList.map(item => item.count);

        const ageGroups = ageList.map(item => item.studentGrade);
        const ageCounts = ageList.map(item => item.count);

        let ageidx = -1; // 초기값 설정

        ageGroups.forEach(function (item, index, array) {
          if (item === null) {
            ageidx = index; // 해당 인덱스를 저장
          }
        });

        if (ageidx !== -1 && ageidx < ageGroups.length) { // 인덱스 범위 확인
                                                          // ageGroups에서 null 값을 가진 요소 제거
          ageGroups.splice(ageidx, 1);

          // 같은 인덱스를 가진 ageCounts에서 요소 제거
          if (ageidx < ageCounts.length) {
            ageCounts.splice(ageidx, 1);
          }
        }


        // ageGroups.forEach(function (item, index, array) {
        //   if (item === null) {
        //     array[index] = 'Teacher'; // null일 때 "Teacher"로 변경
        //     ageidx = index; // 해당 인덱스를 저장
        //   }
        // });
        //
        // if (ageidx !== -1 && ageidx < ageCounts.length) { // 인덱스 범위 확인
        //   ageCounts.forEach(function (item, index, array) {
        //     if (index === ageidx) {
        //       array[index] -= 1; // 해당 인덱스의 값에서 1을 뺌
        //     }
        //   });
        // }

        const ctx = document.getElementById('grammer-count').getContext('2d');
        const grammerCanvas = document.getElementById('grammer-count');
        grammerCanvas.style.width = '400px';
        grammerCanvas.style.height = '100%';
        const grammerchart = new Chart(ctx, {
          type: 'pie',
          data: {
            labels: grammerLabels,  // 차트의 레이블 (레벨 코드)
            datasets: [{
              label: 'Grammar Levels',
              data: grammerCounts,  // 각 레벨의 카운트
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
              ],
              borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
              ],
              borderWidth: 1
            }]
          },
          options: {
            responsive: true,
            plugins: {
              legend: {
                position: 'top',
              },
              title: {
                display: true,
                text: 'Grammar Levels Distribution'
              }
            }
          },
        });

        const sentenceCtx = document.getElementById('sentence-count').getContext('2d');
        const sentenceChart = new Chart(sentenceCtx, {
          type: 'pie',
          data: {
            labels: sentenceLabels,  // 차트의 레이블 (레벨 코드)
            datasets: [{
              label: 'Sentence Levels',
              data: sentenceCounts,  // 각 레벨의 카운트
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
              ],
              borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
              ],
              borderWidth: 1
            }]
          },
          options: {
            responsive: true,
            plugins: {
              legend: {
                position: 'top',
              },
              title: {
                display: true,
                text: 'Sentence Levels Distribution'
              }
            }
          },
        });

        const wordCtx = document.getElementById('word-count').getContext('2d');
        const wordChart = new Chart(wordCtx, {
          type: 'pie',
          data: {
            labels: wordLabels,  // 차트의 레이블 (레벨 코드)
            datasets: [{
              label: 'Sentence Levels',
              data: wordCounts,  // 각 레벨의 카운트
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
              ],
              borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
              ],
              borderWidth: 1
            }]
          },
          options: {
            responsive: true,
            plugins: {
              legend: {
                position: 'top',
              },
              title: {
                display: true,
                text: 'Word Levels Distribution'
              }
            }
          },
        });


        const ageCtx = document.getElementById('age-group-count').getContext('2d');
        const gradeChart = new Chart(ageCtx, {
          type: 'pie',
          data: {
            labels: ageGroups,
            datasets: [{
              data: ageCounts,
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(201, 203, 207, 0.2)'
              ],
              borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
                'rgba(201, 203, 207, 1)'
              ],
              borderWidth: 1
            }]
          },
          options: {
            responsive: true,
            plugins: {
              legend: {
                position: 'top',
              },
              title: {
                display: true,
                text: 'Distribution of Grades'
              }
            }
          }
        });


      },

      error: function () {
        console.error('[ERROR] 데이터를 불러오던 도중 에러가 발생했습니다');
      }
    });
  }


  function llmData() {
    if (row2yearCheck && row2monthCheck) {

      let yearMonthForm = {
        year : row2Year,
        month : row2Month};

      $.ajax({
        url: '/api/admin/call-llm',
        method: 'POST',
        contentType: "application/json",
        data : JSON.stringify(yearMonthForm),
        success: function (data) {

          // let lectureDay = lectureList.map(item => item.day);  // 날짜들
          // let lectureCount = lectureList.map(item => item.count);  // 날짜별 count

          const createDay = data.map(item => item.day);  // 레벨 코드들
          const createCount = data.map(item => item.count);  // 각 레벨에 해당하는 카운트

          if (createDay.length === 0) {
            if (llmChart) {
              llmChart.destroy(); // 기존 차트 삭제
            }
            $("#llmChart").css("display","none");
            $("#chart-wrap2").css("display","none");
            $("#no-lecture").removeClass("none");
          } else {
            // 기존 차트가 존재하면 삭제
            if (llmChart) {
              llmChart.destroy();
            }
            $("#llmChart").css("display","block");
            $("#chart-wrap2").css("display","block");
            $("#no-lecture").addClass("none");
            // Chart.js를 사용하여 차트를 생성합니다.
            const ctx = document.getElementById('llmChart').getContext(
                '2d');
            llmChart = new Chart(ctx, {
              type: 'bar', // 막대 그래프를 생성합니다.
              data: {
                labels: createDay, // X축에 표시될 날짜들
                datasets: [{
                  label: 'Daily Call LLM API Count', // 데이터셋의 레이블
                  data: createCount, // Y축에 표시될 수업 개설 수
                  backgroundColor: 'rgba(75, 192, 192, 0.2)', // 막대의 배경색
                  borderColor: 'rgba(75, 192, 192, 1)', // 막대의 경계선 색
                  borderWidth: 1 // 경계선의 두께
                }]
              },
              options: {
                scales: {
                  y: {
                    beginAtZero: true, // Y축이 0에서 시작하도록 설정
                    ticks: {
                      stepSize: 1, // 눈금의 간격을 1로 설정하여 정수만 표시
                      callback: function (value) {
                        if (Number.isInteger(value)) {
                          return value; // 정수만 표시
                        }
                      }
                    }
                  }
                }
              }
            });
          }
          console.log(createDay);
          console.log(createCount);
        },

        error: function () {
          console.error('[ERROR] 데이터를 불러오던 도중 에러가 발생했습니다');
        }
      });

    }}

  function lectureData() {
    if (row4yearCheck && row4monthCheck) {

      let yearMonthForm = {
        year : row4Year,
        month : row4Month
      };

      $.ajax({
        url: '/api/admin/create-lecture',
        method: 'POST',
        contentType: "application/json",
        data : JSON.stringify(yearMonthForm),
        success: function (data) {
          console.log("성공");

          const createDay = data.map(item => item.day);  // 레벨 코드들
          const createCount = data.map(item => item.count);  // 각 레벨에 해당하는 카운트

          if (createDay.length === 0) {
            if (lectureChart) {
              lectureChart.destroy(); // 기존 차트 삭제
            }
            $("#lectureChart").css("display","none");
            $("#chart-wrap4").css("display","none");
            $("#no-llm").removeClass("none");
          } else {
            // 기존 차트가 존재하면 삭제
            if (lectureChart) {
              lectureChart.destroy();
            }
            $("#lectureChart").css("display","block");
            $("#chart-wrap4").css("display","block");
            $("#no-llm").addClass("none");
            // Chart.js를 사용하여 차트를 생성합니다.
            const ctx = document.getElementById('lectureChart').getContext(
                '2d');
            lectureChart = new Chart(ctx, {
              type: 'bar', // 막대 그래프를 생성합니다.
              data: {
                labels: createDay, // X축에 표시될 날짜들
                datasets: [{
                  label: 'Daily Creation of Lecture Count', // 데이터셋의 레이블
                  data: createCount, // Y축에 표시될 수업 개설 수
                  backgroundColor: 'rgba(75, 192, 192, 0.2)', // 막대의 배경색
                  borderColor: 'rgba(75, 192, 192, 1)', // 막대의 경계선 색
                  borderWidth: 1 // 경계선의 두께
                }]
              },
              options: {
                scales: {
                  y: {
                    beginAtZero: true, // Y축이 0에서 시작하도록 설정
                    ticks: {
                      stepSize: 1, // 눈금의 간격을 1로 설정하여 정수만 표시
                      callback: function (value) {
                        if (Number.isInteger(value)) {
                          return value; // 정수만 표시
                        }
                      }
                    }
                  }
                }
              }
            });
          }




        },

        error: function () {
          console.error('[ERROR] 데이터를 불러오던 도중 에러가 발생했습니다');
        }
      });

    }
  }

  userData();

  let row2yearCheck = false;
  let row2monthCheck = false;
  let row2Year = $("#row2year").val();
  let row2Month = $("#row2month").val();
  let llmChart;
  $("#row2year").change(function(){
    row2yearCheck = true;
    row2Year = $("#row2year").val();
    llmData();
  })
  $("#row2month").change(function(){
    row2monthCheck = true;
    row2Month = $("#row2month").val();
    llmData();
  })

  let row4yearCheck = false;
  let row4monthCheck = false;
  let row4Year = $("#row4year").val();
  let row4Month = $("#row4month").val();
  let lectureChart;

  $("#row4year").change(function(){
    row4yearCheck = true;
    row4Year = $("#row4year").val();
    lectureData();
  })
  $("#row4month").change(function(){
    row4monthCheck = true;
    row4Month = $("#row4month").val();
    lectureData();
  })




})