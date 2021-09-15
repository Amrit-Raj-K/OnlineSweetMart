import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-page-not-found',
  template: `
  <head>
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@600;900&display=swap" rel="stylesheet">
  </head>
  <body>
    <div class="mainbox " >
      <div class="err backgr-text">4</div>
      <div class="err1 backgr-text">0</div>
      <div class="err2 backgr-text">4</div>
      <br>
      <div class="msg">Ooops! <p>Let's go <a href="home" style="color:black">Home</a>, right away...</p></div>
        </div>
        </body>
  `,
  styleUrls: ['./page-not-found.component.css']
})
export class PageNotFoundComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
