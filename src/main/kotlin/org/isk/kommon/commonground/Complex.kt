/**
 * Copyright (c) 2023, Independent Society of Knowledge
 * All rights reserved.
 *
 * Author: Amir H. Ebrahimnezhad
 * Contact: projects@thisismeamir.com
 *
 * This code is the intellectual property of the Independent Society of Knowledge,
 * a community of scientists and researchers in different areas. This library is under
 * the license of GPL-3.0.
 */

package org.isk.kommon.commonground

import kotlin.math.*


/**
 * Represents complex numbers in Cartesian form (a + ib) and provides operations and functions for complex arithmetic.
 * The class supports basic arithmetic operations between complex numbers, as well as operations with real numbers
 * (doubles and integers). It includes functions for obtaining the conjugate, magnitude, and modulus of a complex number,
 * as well as power functions for integer exponents. The class also provides methods for accessing the real and imaginary
 * parts, calculating additive and multiplicative inverses, and more.
 */
@JvmInline
value class Complex(
    val realAndImag: Pair<Double, Double>
) {

    constructor(real: Double, imag: Double) : this(real to imag)

    val real: Double get() = realAndImag.first
    val imag: Double get() = realAndImag.second

    // Binary Operators - Complex, Complex

    /**
     * Adds two complex numbers.
     * The result is a new complex number with the sum of their real and imaginary parts.
     *
     * @param other The complex number to be added.
     * @return A new complex number representing the sum.
     */
    operator fun plus(other: Complex): Complex {
        return Complex(this.real + other.real, this.imag + other.imag)
    }

    /**
     * Subtracts one complex number from another.
     * The result is a new complex number with the difference of their real and imaginary parts.
     *
     * @param other The complex number to be subtracted.
     * @return A new complex number representing the difference.
     */
    operator fun minus(other: Complex): Complex {
        return Complex(this.real - other.real, this.imag - other.imag)
    }

    /**
     * Multiplies two complex numbers.
     * The result is a new complex number obtained by multiplying and combining their real and imaginary parts.
     *
     * @param other The complex number to be multiplied.
     * @return A new complex number representing the product.
     */
    operator fun times(other: Complex): Complex {
        return Complex(
            real = this.real * other.real - this.imag * other.imag,
            imag = this.real * other.imag + this.imag * other.real
        )
    }

    /**
     * Divides one complex number by another.
     * The result is a new complex number obtained by dividing and combining their real and imaginary parts.
     *
     * @param other The complex number to be divided by.
     * @return A new complex number representing the quotient.
     */
    operator fun div(other: Complex): Complex {
        return this * other.multiplicativeInverse()
    }

    /**
     * Adds a real number to the complex number.
     * The result is a new complex number with the real part increased by the given real number,
     * while the imaginary part remains unchanged.
     *
     * @param other The real number to be added.
     * @return A new complex number representing the sum.
     */
    operator fun plus(other: Double): Complex {
        return Complex(this.real + other, this.imag)
    }

    /**
     * Subtracts a real number from the complex number.
     * The result is a new complex number with the real part decreased by the given real number,
     * while the imaginary part remains unchanged.
     *
     * @param other The real number to be subtracted.
     * @return A new complex number representing the difference.
     */
    operator fun minus(other: Double): Complex {
        return Complex(this.real - other, this.imag)
    }

    /**
     * Multiplies the complex number by a real number.
     * The result is a new complex number with both real and imaginary parts scaled by the given real number.
     *
     * @param other The real number to be multiplied.
     * @return A new complex number representing the product.
     */
    operator fun times(other: Double): Complex {
        return Complex(this.real * other, this.imag * other)
    }

    /**
     * Divides the complex number by a real number.
     * The result is a new complex number with both real and imaginary parts divided by the given real number.
     *
     * @param other The real number to be divided by.
     * @return A new complex number representing the quotient.
     */
    operator fun div(other: Double): Complex {
        return Complex(this.real / other, this.imag / other)
    }

    /**
     * Adds an integer to the complex number.
     * The result is a new complex number with the real part increased by the given integer,
     * while the imaginary part remains unchanged.
     *
     * @param other The integer to be added.
     * @return A new complex number representing the sum.
     */
    operator fun plus(other: Int): Complex {
        return Complex(this.real + other, this.imag)
    }

    /**
     * Subtracts an integer from the complex number.
     * The result is a new complex number with the real part decreased by the given integer,
     * while the imaginary part remains unchanged.
     *
     * @param other The integer to be subtracted.
     * @return A new complex number representing the difference.
     */
    operator fun minus(other: Int): Complex {
        return Complex(this.real - other, this.imag)
    }

    /**
     * Multiplies the complex number by an integer.
     * The result is a new complex number with both real and imaginary parts scaled by the given integer.
     *
     * @param other The integer to be multiplied.
     * @return A new complex number representing the product.
     */
    operator fun times(other: Int): Complex {
        return Complex(this.real * other, this.imag * other)
    }

    /**
     * Divides the complex number by an integer.
     * The result is a new complex number with both real and imaginary parts divided by the given integer.
     *
     * @param other The integer to be divided by.
     * @return A new complex number representing the quotient.
     */
    operator fun div(other: Int): Complex {
        return Complex(this.real / other, this.imag / other)
    }

    /**
     * Power function for a complex number raised to an integer exponent.
     * This function raises the complex number to the power of n using the exponentiation by squaring method.
     * It can be expressed more efficiently using polar complexes, which will be implemented later.
     *
     * @param n The integer exponent.
     */
    fun pow(n: Int): Complex {
        return this.toPolar().pow(n).toCartesian()
    }

    /**
     * Power function for a complex number raised to an double exponent.
     * This function raises the complex number to the power of n using the exponentiation by squaring method.
     * It can be expressed more efficiently using polar complexes, which will be implemented later.
     *
     * @param n The double exponent.
     */
    fun pow(n: Double): Complex {
        return this.toPolar().pow(n).toCartesian()
    }

    fun pow(n: Complex): Complex{
        return this.toPolar().run {
            E.pow(n.real * ln(this.magnitude) - this.angle * n.imag) * e(n.imag * ln(this.magnitude) + this.angle * n.real)
        }
    }
    // Unary Operators

    operator fun unaryMinus() = this * -1
    operator fun unaryPlus() = this
    operator fun get(index: Int) = if(index == 0) realAndImag.first else if(index == 1) realAndImag.second else throw IndexOutOfBoundsException("complex is of dimension 2, given: $index")

    /**
     * Returns the real part of the complex number.
     *
     * @return The real part of the complex number.
     */
    fun real(): Double {
        return this.real
    }

    /**
     * Returns the imaginary part of the complex number.
     *
     * @return The imaginary part of the complex number.
     */
    fun imag(): Double {
        return this.imag
    }

    /**
     * Returns the additive inverse (negation) of the complex number.
     * The result is a new complex number with both real and imaginary parts negated.
     *
     * @return A new complex number representing the additive inverse.
     */
    fun additiveInverse(): Complex {
        return Complex(
            real = -this.real,
            imag = -this.imag
        )
    }

    /**
     * Returns the multiplicative inverse (reciprocal) of the complex number.
     * The result is a new complex number with the inverse of the magnitude squared in both real and imaginary parts.
     *
     * @return A new complex number representing the multiplicative inverse.
     */
    fun multiplicativeInverse(): Complex {
        val magnitudeSquared = this.real.pow(2) + this.imag.pow(2)
        return Complex(
            real = this.real / magnitudeSquared,
            imag = -this.imag / magnitudeSquared
        )
    }

    /**
     * Returns the conjugate of the complex number.
     * The result is a new complex number with the same real part and negated imaginary part.
     *
     * @return A new complex number representing the conjugate.
     */
    fun conjugate(): Complex {
        return Complex(real, -imag)
    }

    /**
     * Converts the complex number in Cartesian form to polar form.
     * The result is a new complex number in polar form.
     *
     * @return A new complex number in polar form.
     */
    fun toPolar(): PolarComplex {
        return PolarComplex(
            this.magnitude() to
                    when {
                        this.real > 0 && this.imag >= 0 -> atan(this.imag / this.real)
                        this.real > 0 && this.imag < 0 -> atan(this.imag / this.real) + 2 * Math.PI
                        this.real < 0 -> atan(this.imag / this.real) + Math.PI
                        this.real.toInt() == 0 && this.imag > 0 -> Math.PI / 2
                        this.real.toInt() == 0 && this.imag < 0 -> 3 * Math.PI / 2
                        else -> throw ArithmeticException("Undefined angle for a complex number with real and imaginary parts both equal to 0.")
                    }
        )
    }

    /**
     * Returns the magnitude (absolute value) of the complex number.
     * The result is the square root of the sum of the squares of the real and imaginary parts.
     *
     * @return The magnitude of the complex number.
     */
    fun magnitude(): Double {
        return sqrt(real.pow(2) + imag.pow(2))
    }

    /**
     * Returns the modulus (absolute value) of the complex number.
     * The result is the square root of the sum of the squares of the real and imaginary parts.
     *
     * @return The modulus of the complex number.
     */
    fun modulus(): Double {
        return sqrt(real.pow(2) + imag.pow(2))
    }


    override fun toString(): String =
        "$real ${if (sign(imag) >= 0) '+' else '-'} ${imag.absoluteValue}i"

    companion object {
        fun fromPolar(mag: Double, angle: Double): Complex = PolarComplex(mag to angle).toCartesian()
    }
}


/**
 * Represents complex numbers in Polar form (magnitude, angle) and provides operations and functions for complex arithmetic.
 * The class supports basic arithmetic operations between complex numbers in polar form, as well as operations with real
 * numbers (doubles and integers). It includes functions for obtaining the Cartesian form, conjugate, magnitude, and modulus
 * of a complex number, as well as power functions for integer exponents. The class also provides methods for accessing
 * the magnitude and angle, calculating additive and multiplicative inverses, and more.
 */
@JvmInline
value class PolarComplex(
    val magAndAngle: Pair<Double, Double>
) {
    val mag: Double get() =  magAndAngle.first
    val angle: Double get()=  magAndAngle.second

    constructor(mag:Double, angle: Double): this(mag to angle)
    /**
     * Returns the magnitude of the complex number in polar form.
     * The result is the same as the magnitude property.
     *
     * @return The magnitude of the complex number.
     */
    val magnitude: Double get() = magAndAngle.first

    /**
     * Returns the modulus of the complex number in polar form.
     * The result is the same as the magnitude property.
     *
     * @return The modulus of the complex number.
     */
    val modulus: Double get() = magnitude


    /**
     * Converts the complex number in polar form to Cartesian form.
     * The result is a new complex number in Cartesian form.
     *
     * @return A new complex number in Cartesian form.
     */
    fun toCartesian(): Complex {
        return magnitude * e(angle)
    }

    /**
     * Raises the complex number in polar form to the power of an integer exponent.
     * The result is a new complex number in polar form representing the power.
     *
     * @param n The integer exponent.
     * @return A new complex number in polar form representing the power.
     */
    fun pow(n: Int): PolarComplex {
        val newMag = this.magnitude.pow(n)
        val newAngle = this.angle * n
        return PolarComplex(newMag to newAngle)
    }

    /**
     * Raises the complex number in polar form to the power of an double exponent.
     * The result is a new complex number in polar form representing the power.
     *
     * @param n The integer exponent.
     * @return A new complex number in polar form representing the power.
     */
    fun pow(n: Double): PolarComplex {
        val newMag = magnitude.pow(n)
        val newAngle = angle * n
        return PolarComplex(newMag to newAngle)
    }


}

typealias euler = (angle: Double)-> Complex

val e: euler = {angle->
    Complex(
        cos(angle), sin(angle)
    )

}
val i = Complex(0.0, 1.0)
fun Number.toComplex() = Complex(toDouble(), 0.0)
operator fun Number.plus(c: Complex) = c + this.toDouble()
operator fun Number.minus(c: Complex) = -c + this.toDouble()
operator fun Number.times(c: Complex) = c * this.toDouble()
operator fun Number.div(c: Complex) = this.toComplex() / c

