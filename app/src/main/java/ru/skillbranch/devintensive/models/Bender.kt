package ru.skillbranch.devintensive.models

import android.os.AsyncTask
import androidx.core.text.isDigitsOnly

class Bender(var status: Status = Status.NORMAL, var question:Question = Question.NAME){

    fun askQuestion():String = when(question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer:String) : Pair<String,Triple<Int,Int,Int>> {
        if (question.validate(answer)) {
            return if (question.answers.contains(answer.toLowerCase())) {
                question = question.nextQuestion()
                "Отлично - ты справился\n${question.question}" to status.color
            } else {
                if (status.equals(Status.CRITICAL)) {
                    question = Question.NAME
                    status = Status.NORMAL
                    "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                }else{status = status.nextStatus()
                "Это неправильный ответ\n${question.question}" to status.color
                }
            }
        } else { return "${question.validation}" to status.color
        }
    }


    enum class Status (val color: Triple<Int,Int,Int>){
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,0,0));

        fun nextStatus():Status{
            return if (this.ordinal< values().lastIndex) {
                values()[this.ordinal + 1]
            }else{
                values()[0]
            }
        }
    }
    enum class Question (val question:String, val answers:List<String>,val validation:String){
        NAME("Как меня зовут?",listOf("бендер","bender"),"Имя должно начинаться с заглавной буквы") {
            override fun nextQuestion(): Question = PROFESSION
            override fun validate(answer: String):Boolean = answer[0].isUpperCase()
        },
        PROFESSION("Назови мою профессию?",listOf("сгибальщик","bender"),"Профессия должна начинаться со строчной буквы") {
            override fun nextQuestion(): Question = MATERIAL
            override fun validate(answer: String):Boolean = answer[0].isLowerCase()
        },
        MATERIAL("Из чего я сделан?",listOf("металл","дерево","metal","iron","wood"),"Материал не должен содержать цифр") {
            override fun nextQuestion(): Question = BDAY
            override fun validate(answer: String):Boolean = Regex("""\d+""").containsMatchIn(answer).not()
        },
        BDAY("Когда меня создали?",listOf("2993"),"Год моего рождения должен содержать только цифры") {
            override fun nextQuestion(): Question = SERIAL
            override fun validate(answer: String):Boolean = answer.isDigitsOnly()

        },
        SERIAL("Мой серийный номер?",listOf("2716057"),"Серийный номер содержит только цифры, и их 7") {
            override fun nextQuestion(): Question = IDLE
            override fun validate(answer: String):Boolean = answer.isDigitsOnly()&&answer.length==7
        },
        IDLE("На этом все, вопросов больше нет",listOf(),"") {
            override fun nextQuestion(): Question = IDLE
            override fun validate(answer: String):Boolean = true
        };

        abstract fun nextQuestion():Question
        abstract fun validate(answer: String):Boolean


    }
}
