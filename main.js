import './style.css'



const  WIDTH  = 20
const AXIS_Y = 30
const AXIS_X = 16

let canvas = document.getElementById("tetris") 
let divScore = document.getElementById("score")


canvas.width = AXIS_X* WIDTH
canvas.height = AXIS_Y * WIDTH
let context = canvas.getContext("2d")

let imgBlock1 = new Image()
imgBlock1.src = './image/block1.png'

let imgBlock2 = new Image()
imgBlock2.src = './image/block2.png'

context.fillStyle ="black"
context.fillRect(0,0,AXIS_X*WIDTH, AXIS_Y * WIDTH)
context.scale(WIDTH,WIDTH)


function createArea(){
  return Array(AXIS_Y).fill().map(()=>Array(AXIS_X).fill(0))

}

let area =  createArea()
let score = 0 


let shape=[
  [1,1],
  [1,1],
]



let shapes = [
  [
    [1,1],
    [1,1],
  ],
  [
    [0,1,0],
    [1,1,1],
  ],
  [
    [1,1,0],
    [0,1,1],
  ],
  [
    [0,1,1],
    [1,1,0],
  ],

  [
    [1,0,0],
    [1,1,1],
  ],
  [
    [0,0,1],
    [1,1,1],
  ],
  [
    [1,1,1,1],
  ],
  [
    [1],
  ],
  [
    [1,1],
    [0,1],
  ],
  [
    [1,1,1],
    [1,0,1],
  ],
  [
    [0,1,0],
    [1,1,1],
    [0,1,0],
  ],
  [
    [1,0,0],
    [1,1,1],
    [0,1,0],
  ],
  [
    [0,0,1],
    [1,1,1],
    [0,1,0],
  ],
]


function newPiece(){
  return {
    x: AXIS_X/2 - 2,
    y:0,
    shape:shapes[Math.floor(Math.random()*(shapes.length ))],
  }
}
let piece = newPiece()

function draw(){
  context.fillStyle = "#000";
  context.fillRect(0,0,canvas.width,canvas.height)
  area.forEach((row,y)=>{
    row.forEach((value,x)=>{
      if(value === 1){
        // context.fillStyle = "yellow"
        // context.fillRect(x,y,1,1)
        context.drawImage(imgBlock1, 0, 0, 100, 100, x, y, 1, 1)
      }
    })
  })

  piece.shape.forEach((row,y)=>{
    row.forEach((value,x)=>{
      if(value === 1){
        // context.fillStyle = "red"
        // context.fillRect((x+piece.x),(y+piece.y),1,1)

        context.drawImage(imgBlock2, 0, 0, 100, 100,(x+piece.x),(y+piece.y), 1, 1)
      }
    })
  })

}


document.addEventListener("keydown", event =>{
  if( event.key === "ArrowLeft") {
   
    piece.x--
    if(checkCollision()){
      piece.x++
   }
    
  }
  if( event.key === "ArrowRight"){
      piece.x++
    if(checkCollision()){
    piece.x--
    }
  } 
  if( event.key === "ArrowUp"){
    // piece.y--
    // if(checkCollision()){
    //     piece.y++
    // }
    rotateShape()
  } 
  if( event.key === "ArrowDown"){
    piece.y++
    if(checkCollision()){
      piece.y--
      solidify()
      removeLine()
      piece = newPiece()
    }
  }
  if( event.key === " "){
    rotateShape()
  }
})

function checkCollision(){
  return piece.shape.find((row,y)=>{
    return row.find((value,x)=>{
      return value!==0 
      && area[y + piece.y]?.[x+piece.x] !==0;
    })
  })
}

function solidify(){
 piece.shape.forEach((row,y)=>{
  row.forEach((value,x)=>{
    if(value === 1){
      area[y+piece.y][x+piece.x]=1
    }
  })
 })
}

function removeLine(){
  let rowsToRemove = []
  
  area.forEach((row,y)=>{
    if( row.findIndex(x=>x===0)==-1 ){
        rowsToRemove.push(y)
        score +=10;
    }
  })

  rowsToRemove.forEach(y=>{
    area.splice(y,1)
    area.splice(0,0,Array(AXIS_X).fill(0))
  })
}

let counter = 0
let last_time = 0


function run(time = 0 ){
  
  draw()
  divScore.innerHTML = score;
  window.requestAnimationFrame(run)
}


function move(){

    piece.y++
    counter = 0
    if(checkCollision()){
      piece.y--
      solidify()
      removeLine()
      piece = newPiece()
    }
  
  setTimeout(move,500)
}


function rotateShape(){
  let Y = piece.shape.length;
  let X = piece.shape[0].length;
  let rotatedPiece =[]
  for(let i = 0 ; i < X;i++){
    let row=[];
    for(let j = 0 ; j < Y;j++){
      row.push(piece.shape[Y-j-1][i])  
    }
    rotatedPiece.push(row)
  }
  piece.shape= rotatedPiece;


  if(piece.x + Y >= AXIS_X){
    piece.x =  AXIS_X - Y
  }

}

run()
move()


