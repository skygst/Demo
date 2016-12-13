package com.gst.move.utils;

import android.support.v4.app.Fragment;

import com.testfragandvp.fragment.FragmentGame1;
import com.testfragandvp.fragment.FragmentGame10;
import com.testfragandvp.fragment.FragmentGame11;
import com.testfragandvp.fragment.FragmentGame12;
import com.testfragandvp.fragment.FragmentGame14;
import com.testfragandvp.fragment.FragmentGame15;
import com.testfragandvp.fragment.FragmentGame18;
import com.testfragandvp.fragment.FragmentGame19;
import com.testfragandvp.fragment.FragmentGame2;
import com.testfragandvp.fragment.FragmentGame20;
import com.testfragandvp.fragment.FragmentGame3;
import com.testfragandvp.fragment.FragmentGame4;
import com.testfragandvp.fragment.FragmentGame5;
import com.testfragandvp.fragment.FragmentGame6;
import com.testfragandvp.fragment.FragmentGame7;
import com.testfragandvp.fragment.FragmentGame8;
import com.testfragandvp.fragment.FragmentGame9;
import com.testfragandvp.fragment.FragmentNull;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame1;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame10;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame11;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame12;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame13;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame14;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame15;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame16;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame17;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame18;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame19;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame2;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame20;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame3;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame4;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame5;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame6;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame7;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame8;
import com.testfragandvp.fragment.levelb.FragmentLevelBGame9;

/**
 * 选择题-数据
 * 
 * @author
 * 
 */
public class MultipleChoiceContent {

	public static Fragment[] fragmentGame = new Fragment[] {
			new FragmentGame1(), new FragmentGame2(), new FragmentGame3(),
			new FragmentGame4(), new FragmentGame5(), new FragmentGame6(),
			new FragmentGame7(), new FragmentGame8(), new FragmentGame9(),
			new FragmentGame10(), new FragmentGame11(), new FragmentGame12(),
			new FragmentNull(), new FragmentGame14(), new FragmentGame15(),
			new FragmentNull(), new FragmentNull(), new FragmentGame18(),
			new FragmentGame19(), new FragmentGame20() };

	public static Fragment[] fragmentLevelBGame = new Fragment[] {
			new FragmentLevelBGame1(), new FragmentLevelBGame2(),
			new FragmentLevelBGame3(), new FragmentLevelBGame4(),
			new FragmentLevelBGame5(), new FragmentLevelBGame6(),
			new FragmentLevelBGame7(), new FragmentLevelBGame8(),
			new FragmentLevelBGame9(), new FragmentLevelBGame10(),
			new FragmentLevelBGame11(), new FragmentLevelBGame12(),
			new FragmentLevelBGame13(), new FragmentLevelBGame14(),
			new FragmentLevelBGame15(), new FragmentLevelBGame16(),
			new FragmentLevelBGame17(), new FragmentLevelBGame18(),
			new FragmentLevelBGame19(), new FragmentLevelBGame20(), };

	public static String[] titles = new String[] { "Birds Goes Home",
			"Hamster Home", "He Runs", "I Can", "I See My Colors", "My Dog",
			"My Hair", "My Room", "Pond Animals", "The Forest", "These shoes",
			"What I Like", "Baa Baa Black sheep", "Carlos Counts Kittens",
			"Carlos Goes To School", "Humpty Dumpty", "I Had A Little Hen",
			"In and Out", "The Big Cat", "Up and Down" };

	public static String[] levelBTitles = new String[] { "Apple Sauce",
			"I Pick Up", "I Read A Book", "The Picnic", "We Make Cookies",
			"Birthday Part", "How Many", "On The Farm", "I Love Art Class",
			"Three Baby Birds", "Go Animals Go", "Game We Play", "Where",
			"Playful Puppy", "You And I", "You Can Go", "The Hungry Goat",
			"Carlos And His Teacher", "It Is Spring", "The Big Game" };

	// 选择题答案
	public static String[][] choiceAnswer = new String[][] {
			{ "A", "B", "B", "A", "A" }, { "B", "A", "A", "A", "B" },
			{ "B", "A", "A", "B", "A" }, { "B", "A", "A", "B", "B" },
			{ "A", "A", "B", "B", "A" }, { "A", "A", "B", "A", "A" },
			{ "A", "B", "B", "B", "B" }, { "B", "A", "A", "B", "A" },
			{ "A", "A", "A", "A", "B" }, { "A", "B", "B", "A", "A" },
			{ "B", "B", "A", "B", "A" }, { "A", "A", "B", "A", "B" },
			{ "", "", "", "", "" }, { "B", "A", "A", "A", "B" },
			{ "A", "B", "A", "B", "B" }, { "", "", "", "", "" },
			{ "", "", "", "", "" }, { "A", "B", "B", "A", "A" },
			{ "A", "B", "B", "B", "A" }, { "A", "B", "B", "B", "A" }, };

	public static String[][] issueContent = new String[][] {
			{ "What happened after the bird goes over the city?",
					"How many things did the bird go over?",
					"What happened before the bird goes over the mountains?",
					"The author’s purpose for writing this book is ______",
					"The word farm means ______" }, // 1
			{
					"This book is mostly about ______",
					"The author’s purpose is ______",
					"The hamster has a ball. The word ball belongs in which group?",
					"How does the hamster feel about his home?",
					"Which of the following words means a small animal that looks like a mouse?" }, // 2
			{ "What happens after the boy runs to the boat?",
					"Why does the boy ride a bus?",
					"Why does the boy run everywhere?",
					"How many places did the boy run to?", "A boat goes ______" }, // 3
			{
					"What is this book mostly about?",
					"What can be done inside a house?",
					"What can be done using only your body ______ and nothing else?",
					"How are running and jumping alike?",
					"Which word means the opposite of play?" }, // 4 I can
			{
					"Which word describes the color of the sky in this book?",
					"This book is mostly about ______",
					"The park is the	 ______ in this story.",
					"Which words describe when the story most likely takes place?",
					"Which of the following words means the color of chocolate, coffee, and dirt?" }, // 5
																										// I
																										// See
																										// My
																										// Colors
			{
					"What is the best summary of this book?",
					"What is something the dog can do?",
					"What is something the dog cannot do?",
					"Which of the following best describes the dog in this book?",
					"Finish this sentence: I  ______ on the chair." }, // 6 My
																		// Dog
			{ "What is this book mostly about?", "What is a kind of hair?",
					"What is not a kind of hair?",
					"How do the people in this book feel about their hair?",
					"Which word means the opposite of curly?" }, // 7 My hair
			{
					"What is this book mostly about?",
					"How are shoes and toys alike?",
					"How is a bed different from a desk?",
					"Which of the following could be found in the room in this book?",
					"What do you wear to cover your body?" }, // 8 My room
			{ "Which animals were not found in the water at this pond?",
					"How are swans different from ducks?",
					"The author’s purpose for this story is ______ .",
					"What is another animal that could live in the pond?",
					"What is a pond?" }, // 9 Pond Animals
			{ "A tree belongs to which group?",
					"The forest is the ______ of this story.",
					"Which animal lives in a forest?",
					"______ are found in a forest.",
					"______ is made of water and is smaller than a river." }, // 10
																				// The
																				// Forest
			{
					"What is the main idea of this book?",
					"How are all the shoes in this book the same?",
					"How are the tall shoes different from the shiny shoes?",
					"What is one way that the pairs of funny shoes are different?",
					"Listen to this sentence: That dog looks as big as a horse. Another word for big is ______." }, // 11"These shoes"
			{
					"Which two things are animals?",
					"What is something the main character would play with inside?",
					"Why can’t the girl go outside?",
					"What is another good title for this story?",
					"If you like something, you feel ______." }, // 12
																	// "What I Like"
			{ "", "", "", "", "" }, // 13 "Baa Baa Black sheep"
			{
					"How does Carlos feel about having many kittens?",
					"How many kittens does Carlos have at the end of the story?",
					"What is the main problem in this story?",
					"How can Carlos solve his problem?", "What is a kitten?" }, // 14
																				// "Carlos Counts Kittens"
			{ "What is this story mostly about?",
					"How does Carlos feel when he gets his hug?",
					"What does Carlos get before he gets his eraser?",
					"What does Carlos get after he gets his sister?",
					"Finish this sentence: I put on a ______ to keep me warm." }, // 15
																					// "Carlos Goes To School"
			{ "", "", "", "", "" }, // 16 "Humpty Dumpty"
			{ "", "", "", "", "" }, // 17 "I Had A Little Hen"
			{ "What is this book mostly about?",
					"What happens at the end of the story?",
					"Which animals are not in the story?",
					"Why aren’t the cats happy after they go in the mud?",
					"Which picture shows a dog?" }, // 18 "In and Out"
			{ "What is a detail in the story?",
					"The cat in this story is ______.",
					"What is the main idea of this story?",
					"What happens after the big cat cleans?",
					"Which word means goes out of sight?" }, // 19 "The Big Cat"
			{ "What is this book mostly about?",
					"How are a frog and a dog alike?",
					"Why does the frog go up?",
					"What do the boy and girl both put up and down?",
					"Which word means the opposite of up?" }, // 20
																// "Up and Down"
	};

	public static String[][] answerContent = new String[][] {
			{ "The bird goes over the street.;The bird goes over the farm.",
					"5;7", "The bird goes home.;The bird goes over the cars. ",
					"to tell a story;to give information",
					"land for crops and animals;a place to swim", }, // 1
			{
					"what a hamster eats;what a hamster needs",
					"to give information about hamsters;to tell a story about hamsters",
					"a group of toys;a group of foods", "happy;mad",
					"a home;a hamster" }, // 2
			{ "The boy runs to the train.;The boy runs to the plane.",
					"The boy is going to school.;The boy is going swimming.",
					"The boy likes to run.;The boy wants to be late.", "6;8",
					"in the water;in the sky" }, // 3
			{
					"Kids can run.;Kids can do many things.",
					"crawling;climbing a tree",
					"hopping and jumping;swinging and riding",
					"You need to use a	helmet to do both.;You use your legs to do both.",
					"hop;work" }, // 4 I can
			{ "blue;brown",
					"colors of different objects;hings to take to the park",
					"character;setting", "in winter;in spring", "brown;green" }, // 5
																					// I
																					// See
																					// My
																					// Colors
			{
					"Dogs can do many things, such as jump, run, and swim.;Some boys have dogs, and they are lots of fun.",
					"climb;fly", "dig;read", "full of energy;very lazy",
					"sit;walk" }, // 6 My Dog
			{
					"People have different kinds of hair.;People have the same kind of hair.",
					"cold;short",
					"long;scratchy",
					"They each want their hair to be like everyone else’s hair.;They each like their hair the way it is.",
					"long;straight" }, // 7 My hair
			{
					"All rooms look the same.;Many things can be found in a room.",
					"They both can be found in a room.;They both can be worn on the body.",
					"A bed is used for sleeping, but a desk is not.;A bed is used for doing schoolwork, but a desk is not.",
					"a car;a clock", "clothes;books" }, // 8 My room
			{ "the worms;the ducks",
					"Swans have long necks.;Swans have short necks.",
					"to inform;to tell a story", "an alligator;a bear",
					"a kind of animal;a small body of water" }, // 9 Pond
																// Animals
			{ "plants;animals", "main character;setting", "a whale;a fox",
					"Trees;Gardens", "A stream;A forest" }, // 10 The Forest
			{
					"Shoes are important to wear.;There are many types of shoes.",
					"They all are funny.;They all are worn on the feet.",
					"They are too big.;They cover the legs and feet.",
					"One pair has eyes, and the other pair does not have eyes.;One pair is green, and the other pair is brown.",
					"large;small" }, // 11 "These shoes"
			{ "bees and birds;swings and slides", "a doll;a bike",
					"It is nighttime.;It is raining.",
					"Things I See and Do Outside;Things I See and Do Inside",
					"sad;happy" }, // 12 "What I Like"
			{ "", "", "", "", "" }, // 13 "Baa Baa Black sheep"
			{
					"He feels sad.;He feels happy.",
					"seven;eight",
					"Carlos has too many kittens.;Carlos does not want lots of kittens.",
					"give some kittens away;get more kittens",
					"a young dog;a young cat" }, // 14 "Carlos Counts Kittens"
			{ "getting ready for school;getting ready to go to bed",
					"scared;happy",
					"Carlos gets his ruler.;Carlos gets his sweater.",
					"Carlos gets his crayons.;Carlos gets his lunch.",
					"ride;sweater" }, // 15 "Carlos Goes To School"
			{ "", "", "", "", "" }, // 16 "Humpty Dumpty"
			{ "", "", "", "", "" }, // 17 "I Had A Little Hen"
			{
					"Animals can move in and out of some things.;Animals like mud and getting dirty.",
					"The cows go in the mud.;The pigs play in the mud.",
					"cows;ducks",
					"They are dirty from the mud.;They are hungry.",
					"pic_;pic_" }, // 18 "In and Out"
			{
					"The big cat eats.;The big cat jumps.",
					"lazy;playful",
					"The big cat sleeps all day.;The big cat does many things.",
					"The big cat plays.;The big cat sleeps.", "hides;looks" }, // 19
																				// "The Big Cat"
			{ "Many things can go up and down.;Many animals like to jump.",
					"They both can only go up.;They both can go up and down.",
					"to try to fly to water;to try to eat the fly",
					"an umbrella;their hands", "down;go" }, // 20 "Up and Down"

	};

	// 选择题音效
	public static String[][] choiceSound = new String[][] {
			{
					"af_book_game_1_1_0.mp3;af_book_game_1_1_1.mp3;af_book_game_1_1_2.mp3",
					"af_book_game_1_2_0.mp3;af_book_game_1_2_1.mp3;af_book_game_1_2_2.mp3",
					"af_book_game_1_3_0.mp3;af_book_game_1_3_1.mp3;af_book_game_1_3_2.mp3",
					"af_book_game_1_4_0.mp3;af_book_game_1_4_1.mp3;af_book_game_1_4_2.mp3",
					"af_book_game_1_5_0.mp3;af_book_game_1_5_1.mp3;af_book_game_1_5_2.mp3" }, // 1
			{
					"af_book_game_2_1_0.mp3;af_book_game_2_1_1.mp3;af_book_game_2_1_2.mp3",
					"af_book_game_2_2_0.mp3;af_book_game_2_2_1.mp3;af_book_game_2_2_2.mp3",
					"af_book_game_2_3_0.mp3;af_book_game_2_3_1.mp3;af_book_game_2_3_2.mp3",
					"af_book_game_2_4_0.mp3;af_book_game_2_4_1.mp3;af_book_game_2_4_2.mp3",
					"af_book_game_2_5_0.mp3;af_book_game_2_5_1.mp3;af_book_game_2_5_2.mp3" }, // 2
			{
					"af_book_game_3_1_0.mp3;af_book_game_3_1_1.mp3;af_book_game_3_1_2.mp3",
					"af_book_game_3_2_0.mp3;af_book_game_3_2_1.mp3;af_book_game_3_2_2.mp3",
					"af_book_game_3_3_0.mp3;af_book_game_3_3_1.mp3;af_book_game_3_3_2.mp3",
					"af_book_game_3_4_0.mp3;af_book_game_3_4_1.mp3;af_book_game_3_4_2.mp3",
					"af_book_game_3_5_0.mp3;af_book_game_3_5_1.mp3;af_book_game_3_5_2.mp3" }, // 3
			{
					"af_book_game_4_1_0.mp3;af_book_game_4_1_1.mp3;af_book_game_4_1_2.mp3",
					"af_book_game_4_2_0.mp3;af_book_game_4_2_1.mp3;af_book_game_4_2_2.mp3",
					"af_book_game_4_3_0.mp3;af_book_game_4_3_1.mp3;af_book_game_4_3_2.mp3",
					"af_book_game_4_4_0.mp3;af_book_game_4_4_1.mp3;af_book_game_4_4_2.mp3",
					"af_book_game_4_5_0.mp3;af_book_game_4_5_1.mp3;af_book_game_4_5_2.mp3" }, // 4
			{
					"af_book_game_5_1_0.mp3;af_book_game_5_1_1.mp3;af_book_game_5_1_2.mp3",
					"af_book_game_5_2_0.mp3;af_book_game_5_2_1.mp3;af_book_game_5_2_2.mp3",
					"af_book_game_5_3_0.mp3;af_book_game_5_3_1.mp3;af_book_game_5_3_2.mp3",
					"af_book_game_5_4_0.mp3;af_book_game_5_4_1.mp3;af_book_game_5_4_2.mp3",
					"af_book_game_5_5_0.mp3;af_book_game_5_5_1.mp3;af_book_game_5_5_2.mp3" }, // 5
			{
					"af_book_game_6_1_0.mp3;af_book_game_6_1_1.mp3;af_book_game_6_1_2.mp3",
					"af_book_game_6_2_0.mp3;af_book_game_6_2_1.mp3;af_book_game_6_2_2.mp3",
					"af_book_game_6_3_0.mp3;af_book_game_6_3_1.mp3;af_book_game_6_3_2.mp3",
					"af_book_game_6_4_0.mp3;af_book_game_6_4_1.mp3;af_book_game_6_4_2.mp3",
					"af_book_game_6_5_0.mp3;af_book_game_6_5_1.mp3;af_book_game_6_5_2.mp3" }, // 6
			{
					"af_book_game_7_1_0.mp3;af_book_game_7_1_1.mp3;af_book_game_7_1_2.mp3",
					"af_book_game_7_2_0.mp3;af_book_game_7_2_1.mp3;af_book_game_7_2_2.mp3",
					"af_book_game_7_3_0.mp3;af_book_game_7_3_1.mp3;af_book_game_7_3_2.mp3",
					"af_book_game_7_4_0.mp3;af_book_game_7_4_1.mp3;af_book_game_7_4_2.mp3",
					"af_book_game_7_5_0.mp3;af_book_game_7_5_1.mp3;af_book_game_7_5_2.mp3" }, // 7
			{
					"af_book_game_8_1_0.mp3;af_book_game_8_1_1.mp3;af_book_game_8_1_2.mp3",
					"af_book_game_8_2_0.mp3;af_book_game_8_2_1.mp3;af_book_game_8_2_2.mp3",
					"af_book_game_8_3_0.mp3;af_book_game_8_3_1.mp3;af_book_game_8_3_2.mp3",
					"af_book_game_8_4_0.mp3;af_book_game_8_4_1.mp3;af_book_game_8_4_2.mp3",
					"af_book_game_8_5_0.mp3;af_book_game_8_5_1.mp3;af_book_game_8_5_2.mp3" }, // 8
			{
					"af_book_game_9_1_0.mp3;af_book_game_9_1_1.mp3;af_book_game_9_1_2.mp3",
					"af_book_game_9_2_0.mp3;af_book_game_9_2_1.mp3;af_book_game_9_2_2.mp3",
					"af_book_game_9_3_0.mp3;af_book_game_9_3_1.mp3;af_book_game_9_3_2.mp3",
					"af_book_game_9_4_0.mp3;af_book_game_9_4_1.mp3;af_book_game_9_4_2.mp3",
					"af_book_game_9_5_0.mp3;af_book_game_9_5_1.mp3;af_book_game_9_5_2.mp3" }, // 9
			{
					"af_book_game_10_1_0.mp3;af_book_game_10_1_1.mp3;af_book_game_10_1_2.mp3",
					"af_book_game_10_2_0.mp3;af_book_game_10_2_1.mp3;af_book_game_10_2_2.mp3",
					"af_book_game_10_3_0.mp3;af_book_game_10_3_1.mp3;af_book_game_10_3_2.mp3",
					"af_book_game_10_4_0.mp3;af_book_game_10_4_1.mp3;af_book_game_10_4_2.mp3",
					"af_book_game_10_5_0.mp3;af_book_game_10_5_1.mp3;af_book_game_10_5_2.mp3" }, // 10
			{
					"af_book_game_11_1_0.mp3;af_book_game_11_1_1.mp3;af_book_game_11_1_2.mp3",
					"af_book_game_11_2_0.mp3;af_book_game_11_2_1.mp3;af_book_game_11_2_2.mp3",
					"af_book_game_11_3_0.mp3;af_book_game_11_3_1.mp3;af_book_game_11_3_2.mp3",
					"af_book_game_11_4_0.mp3;af_book_game_11_4_1.mp3;af_book_game_11_4_2.mp3",
					"af_book_game_11_5_0.mp3;af_book_game_11_5_1.mp3;af_book_game_11_5_2.mp3" }, // 11
			{
					"af_book_game_12_1_0.mp3;af_book_game_12_1_1.mp3;af_book_game_12_1_2.mp3",
					"af_book_game_12_2_0.mp3;af_book_game_12_2_1.mp3;af_book_game_12_2_2.mp3",
					"af_book_game_12_3_0.mp3;af_book_game_12_3_1.mp3;af_book_game_12_3_2.mp3",
					"af_book_game_12_4_0.mp3;af_book_game_12_4_1.mp3;af_book_game_12_4_2.mp3",
					"af_book_game_12_5_0.mp3;af_book_game_12_5_1.mp3;af_book_game_12_5_2.mp3" }, // 12
			{}, // 13
			{
					"af_book_game_14_1_0.mp3;af_book_game_14_1_1.mp3;af_book_game_14_1_2.mp3",
					"af_book_game_14_2_0.mp3;af_book_game_14_2_1.mp3;af_book_game_14_2_2.mp3",
					"af_book_game_14_3_0.mp3;af_book_game_14_3_1.mp3;af_book_game_14_3_2.mp3",
					"af_book_game_14_4_0.mp3;af_book_game_14_4_1.mp3;af_book_game_14_4_2.mp3",
					"af_book_game_14_5_0.mp3;af_book_game_14_5_1.mp3;af_book_game_14_5_2.mp3" }, // 14
			{
					"af_book_game_15_1_0.mp3;af_book_game_15_1_1.mp3;af_book_game_15_1_2.mp3",
					"af_book_game_15_2_0.mp3;af_book_game_15_2_1.mp3;af_book_game_15_2_2.mp3",
					"af_book_game_15_3_0.mp3;af_book_game_15_3_1.mp3;af_book_game_15_3_2.mp3",
					"af_book_game_15_4_0.mp3;af_book_game_15_4_1.mp3;af_book_game_15_4_2.mp3",
					"af_book_game_15_5_0.mp3;af_book_game_15_5_1.mp3;af_book_game_15_5_2.mp3" }, // 15
			{}, // 16
			{}, // 17
			{
					"af_book_game_18_1_0.mp3;af_book_game_18_1_1.mp3;af_book_game_18_1_2.mp3",
					"af_book_game_18_2_0.mp3;af_book_game_18_2_1.mp3;af_book_game_18_2_2.mp3",
					"af_book_game_18_3_0.mp3;af_book_game_18_3_1.mp3;af_book_game_18_3_2.mp3",
					"af_book_game_18_4_0.mp3;af_book_game_18_4_1.mp3;af_book_game_18_4_2.mp3",
					"af_book_game_18_5_0.mp3" }, // 18
			{
					"af_book_game_19_1_0.mp3;af_book_game_19_1_1.mp3;af_book_game_19_1_2.mp3",
					"af_book_game_19_2_0.mp3;af_book_game_19_2_1.mp3;af_book_game_19_2_2.mp3",
					"af_book_game_19_3_0.mp3;af_book_game_19_3_1.mp3;af_book_game_19_3_2.mp3",
					"af_book_game_19_4_0.mp3;af_book_game_19_4_1.mp3;af_book_game_19_4_2.mp3",
					"af_book_game_19_5_0.mp3;af_book_game_19_5_1.mp3;af_book_game_19_5_2.mp3" }, // 19
			{
					"af_book_game_20_1_0.mp3;af_book_game_20_1_1.mp3;af_book_game_20_1_2.mp3",
					"af_book_game_20_2_0.mp3;af_book_game_20_2_1.mp3;af_book_game_20_2_2.mp3",
					"af_book_game_20_3_0.mp3;af_book_game_20_3_1.mp3;af_book_game_20_3_2.mp3",
					"af_book_game_20_4_0.mp3;af_book_game_20_4_1.mp3;af_book_game_20_4_2.mp3",
					"af_book_game_20_5_0.mp3;af_book_game_20_5_1.mp3;af_book_game_20_5_2.mp3" }, // 20

	};

	// level_b 题目内容
	public static String[][] issueLevelBContent = new String[][] {
			{
					"What happens before the man cuts the apples?",
					"The author’s purpose for this story is to inform. What is a step the author gives for making applesauce?",
					"The man cooks the apples to ____.",
					"How do the children feel when they are eating the applesauce?",
					"An ____ is a red, yellow, or green fruit that grows on a tree." }, // 1
			{
					"What is the problem in the story?",
					"How does the boy solve the problem?",
					"How many things does the child pick up by the end of the story?",
					"How does the boy’s father feel at the end of the story?",
					"Listen to this sentence: I picked up my blanket. What is a blanket?" }, // 2
			{
					"Who is the first person the girl reads with?",
					"Who does the girl read with before she reads with her brother?",
					"Why does the girl read to her dog instead of with her dog?",
					"How are the girl and her dad alike?", "What is a grandpa?" }, // 3
			{
					"What is this story about?",
					"What is a likely place for Ted and Peg to have their picnic?",
					"Which food do Peg and Ted pack for their picnic?",
					"Which food do Peg and Ted not pack for their picnic?",
					"Ted and Peg put a lot of food in their basket. The word put means ____ ." }, // 4
			{
					"What goes in first?",
					"What goes in before the salt?",
					"What might happen if the girl forgets to put something into the cookie mix?",
					"Why doesn’t the girl put the cookies in the oven?",
					"What is sugar?" }, // 5
			{
					"Where do the girl and boy go first?",
					"What do the boy and girl get at the bakery?",
					"Where do the boy and girl go after the toy store?",
					"What do the children do at the birthday party?",
					"Listen to this sentence: We sing happy birthday. Which word is the opposite of happy?" }, // 6
			{
					"How many candles are on the cake?",
					"If there were one more page at the end of this book, how many things would it likely show?",
					"Which items from the story are found on living things?",
					"How are wings and wheels alike?",
					"Which word means the number between two and four?" }, // 7
			{ "What is this book mostly about?", "Which animals have wings?",
					"How is a cow different from a dog?",
					"Which farm animal plays in the mud?",
					"Which picture shows an animal on a pond?" }, // 8 on the
																	// farm---图
			{
					"What is the main idea of this book?",
					"What is a detail in this book that tells about this main idea?",
					"Who is telling this story?",
					"What does the boy make first?",
					"The boy in this story makes a mess. What word means the opposite of mess?" }, // 9
			{ "What happens at the beginning of this story?",
					"What part of this story cannot really happen?",
					"What happens after each bird grows?",
					"What is the setting for this story?",
					"When the mama bird rests, she is ____ ." }, // 10
			{
					"Which of the following can really happen?",
					"Why did the author likely write this story?",
					"Which of the following is not something the animals in this book use to go places?",
					"How are a bike and a car alike?",
					"Which of the following has wings and flies?" }, // 11
			{ "How are playing baseball and hide and seek alike?",
					"How are playing tag and playing soccer alike?",
					"What should not be played with?",
					"What is this book mostly about?",
					"Finish this sentence: When you play, you ____ ." }, // 12
			{ "Why does little brown bear talk to many animals?",
					"How does little brown bear feel?",
					"How does little brown bear try to solve his problem?",
					"How will little brown bear feel when he is not lost?",
					"What does it mean to be lost?" }, // 13
			{
					"Where does this story take place?",
					"Which of the following is a character in this story?",
					"What is a likely reason why the puppy gets close to the animals?",
					"What can the puppy not play with?",
					"When the girl tells the puppy to “Let it be,” she’s telling the puppy to ____ ." }, // 14
			{
					"What is the main idea of this story?",
					"What is not something that the children did together?",
					"How are the children staying safe when riding their bikes?",
					"Where do you think the children are playing?",
					"Finish this sentence: We like to ____ to the music." }, // 15
			{ "How is over different from under?",
					"What is not something that the dog does?",
					"What is the main idea of this story?",
					"What did the dog do after it went under the log?",
					"Which word means the opposite of backward?" }, // 16
			{
					"The characters in this story are ____ .",
					"What did the goat do after it ate the math problems?",
					"What is the problem in this story?",
					"How does the boy feel when the goat eats the old shoes?",
					"Listen to this sentence: My goat ate the stinky trash. Another word for stinky is ____ ." }, // 17
			{
					"Carlos seems ____ with his teacher.",
					"What is not something Carlos and his teacher do at school?",
					"Which detail supports the main idea of this story?",
					"What do Carlos and his teacher do at the end of the story?",
					"What can Carlos share with his teacher?" }, // 18
			{
					"What happens in spring?",
					"What is not something that happens in spring?",
					"The main idea in this story is many things happen in spring. Which detail supports the main idea?",
					"How are plants and flowers alike?", "What is a worm?" }, // 19
			{ "What happened first in this story?",
					"What is an event from this story?",
					"What is the main idea of this story?",
					"Why did the author write this book?",
					"Which word means to shout in support of something?" }, // 20
	};

	// level_b 选项内容
	public static String[][] answerLevelBContent = new String[][] {
			{
					"He buys some apples.;He mashes the apples.",
					"Cut the apples.;Sort the apples.",
					"make them clean enough to eat;make them soft enough to mash",
					"happy;sad", "apple;applesauce" }, // 1
			{ "The boy has a messy room.;The boy has a clean room.",
					"He cleans his room.;He plays with his toys.",
					"seven;five", "sad;happy",
					"something you cover up with;something you read" }, // 2
			{
					"her mom;her dad",
					"her aunt;her sister",
					"A dog can’t read.;Her dog doesn’t like to read.",
					"They both need shoes to read.;They both need glasses to read.",
					"The father of your mother or father.;The mother of your mother or father." }, // 3
			{ "People take mice on a picnic.;People take food on a picnic.",
					"inside a restaurant;outside at a park", "cake;cheese",
					"oranges;sandwiches",
					"to take something out;to place or set something in" }, // 4
			{
					"eggs;flour",
					"sugar;butter",
					"The girl won’t make cookies again.;The cookies might taste bad.",
					"The girl doesn’t want to bake the cookies.;The oven is too hot for the girl to touch.",
					"a white powder that comes from wheat;something used to make food taste sweet" }, // 5
			{ "the card shop;the balloon shop;the toy store",
					"cupcakes;a cake;cookies",
					"the ice cream shop;the card shop;a birthday party",
					"play games;watch TV;open presents", "angry;sad;funny" }, // 6
			{
					"seven;five;eight",
					"nine;ten;zero",
					"eyes;wheels;windows",
					"They both are round.;They both help things move.;Both A and B",
					"six;three;two" }, // 7
			{ "Many animals live on a farm.;A dog lives in a house.",
					"a goat and a sheep;a chicken and a duck",
					"A cow lives in a barn.;A cow lives in a house.",
					"a chicken;a pig", "pic_;pic_" }, // 8 TODO 有图
			{
					"A boy loves making hats in art class.;A boy makes many things in art class.",
					"The boy makes a flower.;The boy loves art.",
					"the boy;the teacher", "a mess;a drawing", "clean;dirty" }, // 9
			{
					"The baby birds fly away.;The baby birds come out of their eggs.",
					"Birds put on and wear clothes.;Birds live in a nest.",
					"It flies away.;It lays eggs.", "a house;a tree",
					"not doing any work;doing lots of work" }, // 10
			{
					"The animals go.;The animals skate.",
					"to show in a silly way different things that go;to show all the things that real animals can do",
					"a bus;a horse",
					"They both go on water.;They both have wheels.",
					"a plane;a train" }, // 11
			{ "They are both played indoors.;They are both played outside.",
					"You run while playing.;You need a ball to play.",
					"fire;video games",
					"kinds of games to play;places where games are played",
					"have fun;need lots of people" }, // 12
			{ "Little brown bear is lost.;Little brown bear is hungry.",
					"scared;tired",
					"He looks for food.;He asks the animals for help.",
					"sad;happy",
					"not able to find the way to a place;not able to get help" }, // 13
			{
					"inside;outside",
					"a cat;a puppy",
					"The puppy is scared of them.;The puppy wants to play with them.",
					"a ball;a turtle", "leave it alone;play with it" }, // 14
			{
					"There are many ways to have fun with another person.;There are many things to do in the summer.",
					"eat dinner;run",
					"They are wearing helmets.;They are riding in the street.",
					"in the backyard;at a park", "hike;dance" }, // 15
			{
					"Going over is going above.;Going over is going below.",
					"go under a bridge;go through a puddle",
					"There are many ways to go from place to place.;Dogs like to run around.",
					"It went around the bush.;It went over the log.",
					"across;forward" }, // 16
			{
					"a boy and a goat;a boy and his brother",
					"The goat ate the old shoes.;The goat ate the big book.",
					"The boy could not find his new ball.;The boy has a goat that eats everything.",
					"angry;worried", "smelly;tasty" }, // 17
			{
					"angry;happy",
					"sing;paint",
					"Carlos and his teacher clean.;Carlos and his teacher swim.",
					"write;play", "books;shoes" }, // 18
			{
					"It rains.;It snows.",
					"Leaves fall to the ground.;Flowers grow.",
					"Birds fly away.;Birds pop out.",
					"They both grow in the ground.;They do not need the sun to grow.",
					"a long animal with no bones;a tall animal with legs" }, // 19
			{
					"eating;cleaning",
					"paint faces;watch movies",
					"There are lots of chores to do on game day.;Families do many things on game day.",
					"to try to get people to watch football;to tell a story about game day",
					"cheer;watch" }, // 20
	};

	// level_b 选择题音效
	public static String[][] choiceLevelBSound = new String[][] {
			{ "b_game_1_1_0.mp3;b_game_1_1_1.mp3;b_game_1_1_2.mp3",
					"b_game_1_2_0.mp3;b_game_1_2_1.mp3;b_game_1_2_2.mp3",
					"b_game_1_3_0.mp3;b_game_1_3_1.mp3;b_game_1_3_2.mp3",
					"b_game_1_4_0.mp3;b_game_1_4_1.mp3;b_game_1_4_2.mp3",
					"b_game_1_5_0.mp3;b_game_1_5_1.mp3;b_game_1_5_2.mp3" }, // 1
			{ "b_game_2_1_0.mp3;b_game_2_1_1.mp3;b_game_2_1_2.mp3",
					"b_game_2_2_0.mp3;b_game_2_2_1.mp3;b_game_2_2_2.mp3",
					"b_game_2_3_0.mp3;b_game_2_3_1.mp3;b_game_2_3_2.mp3",
					"b_game_2_4_0.mp3;b_game_2_4_1.mp3;b_game_2_4_2.mp3",
					"b_game_2_5_0.mp3;b_game_2_5_1.mp3;b_game_2_5_2.mp3" }, // 2
			{ "b_game_3_1_0.mp3;b_game_3_1_1.mp3;b_game_3_1_2.mp3",
					"b_game_3_2_0.mp3;b_game_3_2_1.mp3;b_game_3_2_2.mp3",
					"b_game_3_3_0.mp3;b_game_3_3_1.mp3;b_game_3_3_2.mp3",
					"b_game_3_4_0.mp3;b_game_3_4_1.mp3;b_game_3_4_2.mp3",
					"b_game_3_5_0.mp3;b_game_3_5_1.mp3;b_game_3_5_2.mp3" }, // 3
			{ "b_game_4_1_0.mp3;b_game_4_1_1.mp3;b_game_4_1_2.mp3",
					"b_game_4_2_0.mp3;b_game_4_2_1.mp3;b_game_4_2_2.mp3",
					"b_game_4_3_0.mp3;b_game_4_3_1.mp3;b_game_4_3_2.mp3",
					"b_game_4_4_0.mp3;b_game_4_4_1.mp3;b_game_4_4_2.mp3",
					"b_game_4_5_0.mp3;b_game_4_5_1.mp3;b_game_4_5_2.mp3" }, // 4
			{ "b_game_5_1_0.mp3;b_game_5_1_1.mp3;b_game_5_1_2.mp3",
					"b_game_5_2_0.mp3;b_game_5_2_1.mp3;b_game_5_2_2.mp3",
					"b_game_5_3_0.mp3;b_game_5_3_1.mp3;b_game_5_3_2.mp3",
					"b_game_5_4_0.mp3;b_game_5_4_1.mp3;b_game_5_4_2.mp3",
					"b_game_5_5_0.mp3;b_game_5_5_1.mp3;b_game_5_5_2.mp3" }, // 5
			{
					"b_game_6_1_0.mp3;b_game_6_1_1.mp3;b_game_6_1_2.mp3;b_game_6_1_3.mp3",
					"b_game_6_2_0.mp3;b_game_6_2_1.mp3;b_game_6_2_2.mp3;b_game_6_2_3.mp3",
					"b_game_6_3_0.mp3;b_game_6_3_1.mp3;b_game_6_3_2.mp3;b_game_6_3_3.mp3",
					"b_game_6_4_0.mp3;b_game_6_4_1.mp3;b_game_6_4_2.mp3;b_game_6_4_3.mp3",
					"b_game_6_5_0.mp3;b_game_6_5_1.mp3;b_game_6_5_2.mp3;b_game_6_5_3.mp3" }, // 6
			{
					"b_game_7_1_0.mp3;b_game_7_1_1.mp3;b_game_7_1_2.mp3;b_game_7_1_3.mp3",
					"b_game_7_2_0.mp3;b_game_7_2_1.mp3;b_game_7_2_2.mp3;b_game_7_2_3.mp3",
					"b_game_7_3_0.mp3;b_game_7_3_1.mp3;b_game_7_3_2.mp3;b_game_7_3_3.mp3",
					"b_game_7_4_0.mp3;b_game_7_4_1.mp3;b_game_7_4_2.mp3;b_game_7_4_3.mp3",
					"b_game_7_5_0.mp3;b_game_7_5_1.mp3;b_game_7_5_2.mp3;b_game_7_5_3.mp3" }, // 7
			{ "b_game_8_1_0.mp3;b_game_8_1_1.mp3;b_game_8_1_2.mp3",
					"b_game_8_2_0.mp3;b_game_8_2_1.mp3;b_game_8_2_2.mp3",
					"b_game_8_3_0.mp3;b_game_8_3_1.mp3;b_game_8_3_2.mp3",
					"b_game_8_4_0.mp3;b_game_8_4_1.mp3;b_game_8_4_2.mp3",
					"b_game_8_5_0.mp3" }, // 8
			{ "b_game_9_1_0.mp3;b_game_9_1_1.mp3;b_game_9_1_2.mp3",
					"b_game_9_2_0.mp3;b_game_9_2_1.mp3;b_game_9_2_2.mp3",
					"b_game_9_3_0.mp3;b_game_9_3_1.mp3;b_game_9_3_2.mp3",
					"b_game_9_4_0.mp3;b_game_9_4_1.mp3;b_game_9_4_2.mp3",
					"b_game_9_5_0.mp3;b_game_9_5_1.mp3;b_game_9_5_2.mp3" }, // 9
			{ "b_game_10_1_0.mp3;b_game_10_1_1.mp3;b_game_10_1_2.mp3",
					"b_game_10_2_0.mp3;b_game_10_2_1.mp3;b_game_10_2_2.mp3",
					"b_game_10_3_0.mp3;b_game_10_3_1.mp3;b_game_10_3_2.mp3",
					"b_game_10_4_0.mp3;b_game_10_4_1.mp3;b_game_10_4_2.mp3",
					"b_game_10_5_0.mp3;b_game_10_5_1.mp3;b_game_10_5_2.mp3" }, // 10
			{ "b_game_11_1_0.mp3;b_game_11_1_1.mp3;b_game_11_1_2.mp3",
					"b_game_11_2_0.mp3;b_game_11_2_1.mp3;b_game_11_2_2.mp3",
					"b_game_11_3_0.mp3;b_game_11_3_1.mp3;b_game_11_3_2.mp3",
					"b_game_11_4_0.mp3;b_game_11_4_1.mp3;b_game_11_4_2.mp3",
					"b_game_11_5_0.mp3;b_game_11_5_1.mp3;b_game_11_5_2.mp3" }, // 11
			{ "b_game_12_1_0.mp3;b_game_12_1_1.mp3;b_game_12_1_2.mp3",
					"b_game_12_2_0.mp3;b_game_12_2_1.mp3;b_game_12_2_2.mp3",
					"b_game_12_3_0.mp3;b_game_12_3_1.mp3;b_game_12_3_2.mp3",
					"b_game_12_4_0.mp3;b_game_12_4_1.mp3;b_game_12_4_2.mp3",
					"b_game_12_5_0.mp3;b_game_12_5_1.mp3;b_game_12_5_2.mp3" }, // 12
			{ "b_game_13_1_0.mp3;b_game_13_1_1.mp3;b_game_13_1_2.mp3",
					"b_game_13_2_0.mp3;b_game_13_2_1.mp3;b_game_13_2_2.mp3",
					"b_game_13_3_0.mp3;b_game_13_3_1.mp3;b_game_13_3_2.mp3",
					"b_game_13_4_0.mp3;b_game_13_4_1.mp3;b_game_13_4_2.mp3",
					"b_game_13_5_0.mp3;b_game_13_5_1.mp3;b_game_13_5_2.mp3" }, // 13
			{ "b_game_14_1_0.mp3;b_game_14_1_1.mp3;b_game_14_1_2.mp3",
					"b_game_14_2_0.mp3;b_game_14_2_1.mp3;b_game_14_2_2.mp3",
					"b_game_14_3_0.mp3;b_game_14_3_1.mp3;b_game_14_3_2.mp3",
					"b_game_14_4_0.mp3;b_game_14_4_1.mp3;b_game_14_4_2.mp3",
					"b_game_14_5_0.mp3;b_game_14_5_1.mp3;b_game_14_5_2.mp3" }, // 14
			{ "b_game_15_1_0.mp3;b_game_15_1_1.mp3;b_game_15_1_2.mp3",
					"b_game_15_2_0.mp3;b_game_15_2_1.mp3;b_game_15_2_2.mp3",
					"b_game_15_3_0.mp3;b_game_15_3_1.mp3;b_game_15_3_2.mp3",
					"b_game_15_4_0.mp3;b_game_15_4_1.mp3;b_game_15_4_2.mp3",
					"b_game_15_5_0.mp3;b_game_15_5_1.mp3;b_game_15_5_2.mp3" }, // 15
			{ "b_game_16_1_0.mp3;b_game_16_1_1.mp3;b_game_16_1_2.mp3",
					"b_game_16_2_0.mp3;b_game_16_2_1.mp3;b_game_16_2_2.mp3",
					"b_game_16_3_0.mp3;b_game_16_3_1.mp3;b_game_16_3_2.mp3",
					"b_game_16_4_0.mp3;b_game_16_4_1.mp3;b_game_16_4_2.mp3",
					"b_game_16_5_0.mp3;b_game_16_5_1.mp3;b_game_16_5_2.mp3" }, // 16
			{ "b_game_17_1_0.mp3;b_game_17_1_1.mp3;b_game_17_1_2.mp3",
					"b_game_17_2_0.mp3;b_game_17_2_1.mp3;b_game_17_2_2.mp3",
					"b_game_17_3_0.mp3;b_game_17_3_1.mp3;b_game_17_3_2.mp3",
					"b_game_17_4_0.mp3;b_game_17_4_1.mp3;b_game_17_4_2.mp3",
					"b_game_17_5_0.mp3;b_game_17_5_1.mp3;b_game_17_5_2.mp3" }, // 17
			{ "b_game_18_1_0.mp3;b_game_18_1_1.mp3;b_game_18_1_2.mp3",
					"b_game_18_2_0.mp3;b_game_18_2_1.mp3;b_game_18_2_2.mp3",
					"b_game_18_3_0.mp3;b_game_18_3_1.mp3;b_game_18_3_2.mp3",
					"b_game_18_4_0.mp3;b_game_18_4_1.mp3;b_game_18_4_2.mp3",
					"b_game_18_5_0.mp3" }, // 18
			{ "b_game_19_1_0.mp3;b_game_19_1_1.mp3;b_game_19_1_2.mp3",
					"b_game_19_2_0.mp3;b_game_19_2_1.mp3;b_game_19_2_2.mp3",
					"b_game_19_3_0.mp3;b_game_19_3_1.mp3;b_game_19_3_2.mp3",
					"b_game_19_4_0.mp3;b_game_19_4_1.mp3;b_game_19_4_2.mp3",
					"b_game_19_5_0.mp3;b_game_19_5_1.mp3;b_game_19_5_2.mp3" }, // 19
			{ "b_game_20_1_0.mp3;b_game_20_1_1.mp3;b_game_20_1_2.mp3",
					"b_game_20_2_0.mp3;b_game_20_2_1.mp3;b_game_20_2_2.mp3",
					"b_game_20_3_0.mp3;b_game_20_3_1.mp3;b_game_20_3_2.mp3",
					"b_game_20_4_0.mp3;b_game_20_4_1.mp3;b_game_20_4_2.mp3",
					"b_game_20_5_0.mp3;b_game_20_5_1.mp3;b_game_20_5_2.mp3" }, // 20
	};

	// level_b 选择题答案
	public static String[][] choiceLevelBAnswer = new String[][] {
			{ "A", "A", "B", "A", "A" }, { "A", "A", "A", "B", "A" },
			{ "A", "B", "A", "B", "A" }, { "B", "B", "B", "A", "B" },
			{ "B", "A", "B", "B", "B" }, { "A", "B", "C", "A", "B" },
			{ "B", "B", "A", "B", "B" }, { "A", "B", "A", "B", "B" },
			{ "B", "A", "A", "B", "A" }, { "B", "A", "A", "B", "A" },
			{ "A", "A", "A", "B", "A" }, { "B", "A", "A", "A", "A" },
			{ "A", "A", "B", "B", "A" }, { "B", "B", "B", "B", "A" },
			{ "A", "A", "A", "B", "B" }, { "A", "A", "A", "A", "B" },
			{ "A", "B", "B", "A", "A" }, { "B", "A", "A", "B", "A" },
			{ "A", "A", "B", "A", "A" }, { "B", "A", "B", "B", "A" } };

}
