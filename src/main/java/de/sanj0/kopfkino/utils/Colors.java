/*
 *    Copyright 2021 ***REMOVED*** ***REMOVED***
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package de.sanj0.kopfkino.utils;

import java.awt.*;

/**
 * A neat collection of color constants.
 */
public class Colors {

    // Red Colors:
    public static final Color MAROON_RED_COLOR = new Color(128, 0, 0);
    public static final Color TOMATO_RED = new Color(255, 9, 71);
    public static final Color FIREBRICK_RED = new Color(178, 34, 34);
    public static final Color CRIMSON_RED = new Color(220, 20, 60);
    public static final Color HEART_RED = new Color(229, 55, 84);
    public static final Color MODERN_RED = new Color(234, 104, 113);
    public static final Color CORAL_RED = new Color(255, 127, 80);
    public static final Color INDIAN_RED = new Color(205, 92, 92);
    public static final Color DARK_SALMON = new Color(233, 150, 122);
    public static final Color SALMON = new Color(250, 128, 114);
    // Orange Colors:
    public static final Color ORANGE_RED = new Color(255, 69, 0);
    public static final Color DARK_ORANGE = new Color(255, 140, 0);
    public static final Color NEUTRAL_ORANGE = new Color(229, 136, 71);
    public static final Color ORANGE = new Color(255, 165, 0);
    // Green Colors:
    public static final Color GRASS_GREEN = new Color(62, 136, 73);
    public static final Color TREE_GREEN = new Color(12, 174, 91);
    public static final Color OLIVE_COLOR = new Color(128, 128, 0);
    public static final Color DARK_GREEN_COLOR = new Color(0, 128, 0);
    public static final Color YELLOW_GREEN = new Color(154, 205, 50);
    public static final Color DARK_OLIVE_GREEN = new Color(154, 205, 50);
    public static final Color OLIVE_DRAB_GREEN = new Color(107, 142, 35);
    public static final Color FOREST_GREEN = new Color(34, 139, 34);
    public static final Color ACTIVE_GREEN = new Color(104, 204, 87);
    public static final Color LIGHT_GREEN = new Color(144, 238, 144);
    public static final Color PALE_GREEN = new Color(152, 251, 152);
    public static final Color MEDIUM_SPRING_GREEN = new Color(0, 250, 154);
    public static final Color SPRING_GREEN = new Color(0, 250, 127);
    public static final Color SEA_GREEN = new Color(46, 139, 87);
    public static final Color MEDIUM_SEA_GREEN = new Color(60, 179, 113);
    public static final Color HONEY_DEW_GREEN = new Color(240, 255, 240);
    public static final Color MINT_CREAM = new Color(245, 255, 250);
    // Blue Colors:
    public static final Color TEAL_BLUE_COLOR = new Color(0, 128, 128);
    public static final Color NAVY_BLUE_COLOR = new Color(0, 0, 128);
    public static final Color MEDIUM_MARINE_BLUE = new Color(102, 205, 170);
    public static final Color LIGHT_SEA_BLUE = new Color(32, 178, 170);
    public static final Color DARK_TURQUOISE = new Color(0, 206, 209);
    public static final Color TURQUOISE = new Color(64, 224, 208);
    public static final Color AQUA_MARINE_BLUE = new Color(127, 255, 212);
    public static final Color NEUTRAL_BLUE = new Color(62, 134, 160);
    public static final Color CADET_BLUE = new Color(95, 158, 160);
    public static final Color STEEL_BLUE = new Color(70, 130, 180);
    public static final Color CORN_FLOWER_BLUE = new Color(100, 149, 237);
    public static final Color DEEP_SKY_BLUE = new Color(0, 191, 255);
    public static final Color DODGER_BLUE = new Color(30, 144, 255);
    public static final Color MIDNIGHT_BLUE = new Color(25, 25, 112);
    public static final Color ROYAL_BLUE = new Color(65, 105, 255);
    public static final Color OCEAN_BLUE = new Color(0, 119, 190);
    // Yellow and Brown Colors:
    public static final Color GOLD = new Color(255, 215, 0);
    public static final Color DARK_GOLD_ROD = new Color(184, 134, 11);
    public static final Color GOLD_ROD = new Color(218, 165, 32);
    public static final Color KHAKI = new Color(240, 230, 140);
    public static final Color DARK_KHAKI = new Color(240, 230, 140);
    public static final Color SADDLE_BROWN = new Color(139, 69, 19);
    public static final Color SIENNA_BROWN = new Color(160, 82, 45);
    public static final Color CHOCOLATE_BROWN = new Color(210, 105, 30);
    public static final Color PERU_BROWN = new Color(205, 133, 63);
    public static final Color SAND_BROWN = new Color(244, 164, 96);
    public static final Color MOCCASIN = new Color(255, 228, 181);
    public static final Color ROSY_BRON = new Color(188, 143, 143);
    public static final Color TREE_BROWN = new Color(97, 59, 22);
    // Purple Colors:
    public static final Color PURPLE_COLOR = new Color(128, 0, 128);
    public static final Color BLUE_VIOLET = new Color(138, 43, 226);
    public static final Color INDIGO = new Color(75, 0, 130);
    public static final Color DARK_MAGENTA = new Color(139, 0, 139);
    public static final Color ORCHID_PURPLE = new Color(153, 50, 204);
    public static final Color DEEP_PINK = new Color(255, 20, 147);
    // Black to White Colors:
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color SLATE_GRAY = new Color(112, 128, 144);
    public static final Color DARKER_GRAY = new Color(25, 25, 25);
    public static final Color SAD_GRAY = new Color(56, 57, 52);
    public static final Color DARK_GRAY = new Color(50, 50, 50);
    public static final Color GRAY = new Color(105, 105, 105);
    public static final Color LIGHT_GRAY = new Color(150, 150, 150);
    public static final Color LIGHTER_GRAY = new Color(200, 200, 200);
    public static final Color VINTAGE_GRAY_1 = new Color(129, 127, 113);
    public static final Color VINTAGE_GRAY_2 = new Color(75, 74, 58);
    public static final Color VINTAGE_GRAY_3 = new Color(47, 45, 26);
    public static final Color GHOST_WHITE = new Color(248, 248, 255);
    public static final Color SNOW_WHITE = new Color(255, 250, 250);
    public static final Color WHITE_SMOKE = new Color(245, 245, 245);
    public static final Color VINTAGE_WHITE = new Color(217, 215, 200);
    public static final Color WHITE = new Color(255, 255, 255);
    // Other Colors:
    public static final Color SILVER_COLOR = new Color(192, 192, 192);
    public static final Color IVORY = new Color(255, 255, 240);
    public static final Color NAVAJO_WHITE = new Color(255, 222, 173);
    public static final Color PEACH_PUFF = new Color(255, 218, 185);
    public static final Color LAVENDER_BUSH = new Color(255, 228, 225);
    public static final Color PAPAYA_WHIP = new Color(255, 239, 213);
    public static final Color SEA_SHELL = new Color(255, 245, 238);
    public static final Color OLD_LACE = new Color(253, 245, 230);
    public static final Color TRANSPARENT_COLOR = new Color(0, 0, 0, 0);
    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);
    // Plain Colors:
    public static final Color MAX_RED = new Color(255, 0, 0);
    public static final Color RED = new Color(210, 50, 50);
    public static final Color MAX_GREEN = new Color(0, 255, 0);
    public static final Color GREEN = new Color(50, 210, 50);
    public static final Color MAX_BLUE = new Color(0, 0, 255);
    public static final Color BLUE = new Color(50, 50, 210);
    public static final Color MAX_YELLOW = new Color(255, 255, 0);
    public static final Color YELLOW = new Color(210, 210, 50);
    public static final Color MAX_CYAN = new Color(0, 255, 255);
    public static final Color MAX_MAGENTA = new Color(255, 0, 255);
    public static final Color PLAIN_BLACK = new Color(0, 0, 0);
    public static final Color PLAIN_WHITE = new Color(255, 255, 255);

    private Colors() {
    }
}
