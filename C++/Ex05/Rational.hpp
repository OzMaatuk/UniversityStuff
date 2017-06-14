/**
 * Rational - a class that represents a ratioanl number -
 * a ratio between two integers (long ints).
 * 
 * 1. This rational should be represented using two integer values (long int): the numerator
 * (nominator) and the denominator (denominator) of the ratio. 
 * 2. The denominator cannot be zero.
 * 3. The numerator and the denominator should be coprime (MISPARIM ZARIM):
 * their greatest common divisor is 1.
 * 4. The zero element of this field is zero, and it should only be represented
 * with numerator of 0 and denominator of 1. If you reach some other
 * representation that equals 0 (like 0/5 or -0/1) change the representation to
 * 0/1 using private function fixZero().
 * The zero element is the value created by the default constructor (meaning
 * when calling constructor without any arguments) or by the constructor when
 * sending a long int argument of 0.
 * 5. For both add and mul we should remember to make sure the end
 * representation of the ratio has coprime numerator and denominator (TZIMTZUM
 * SHVARIM). For that purpose we should use the greatestCommonDivisor function
 * (private static function) and the makeRepresentationCoprime() private
 * non-static function to take care of it at the end of add and mul functions.
 * 6. If the rational value is positive, both the numerator and the denominator
 * in the representation should be positive.
 * 7. If the ratioanl value is negative, the numerator should be negative and
 * the denominator should be positive. For this, use the fixNegativity()
 * private function.
 * 8. If Rational(const long int& numerator, const long int& denominator)
 * constructor gets a zero denominator, it should fail with an assert.
 */
#pragma once
#include "myassert.hpp"
#include <cstdlib>
#include <cmath>
#include <string>

class Rational
{
	
	public:

	/**
	* Constructors getting a long int or default which is set to 0
	*/
	Rational()
	{
		_numerator = ZEROVAL;
		_denominator = defDenominator;
	}

	/**
	* Constructors getting a long int and define default denominator
	*/
	Rational(const long int& val)
	{
		_numerator = val;
		_denominator = defDenominator;
	}

	/**
	* Constructors getting numerator and denominator
	*/
	Rational(const long int& numerator, const long int& denominator)
	{
		// assert check for zero value of denominator
		myassert(denominator != ZEROVAL);
		
		// sets nominator and denominator
		_numerator=numerator;   
		_denominator=denominator;    
		
		// makeing it a valied Rational uses out finctions
		_fixNegativity();
		_makeRepresentationCoprime();
		_fixZero();
	}

/**
* Constructors by string "numerator/denominator"
*/

Rational(const std::string& str)
{
	int synInx = str.find("/");
	std:: string st1, st2;
	
	// sets the nominator 
	st1 = str.substr(0 , synInx);
	_numerator = std::atol(st1.c_str());
	
	// sets the denominator 
	st2 = str.substr(synInx+1 ,str.length());
	// assert check for zero denominator
	myassert(std::atol(st2.c_str()) != ZEROVAL);
	_denominator = std::atol(st2.c_str());
	
	// makeing it a valied Rational uses out finctions
	_fixNegativity();
	_makeRepresentationCoprime();
	_fixZero();
}

/**
* Returns the numerator
*/
const long int getNumerator() const
{
	return _numerator;
}

/**
* Returns the denominator
*/
const long int getDenominator() const
{
	return _denominator;
}

/*
* Returns true if the Rationals are equal and false otherwise
*/
const bool operator==(const Rational& other) const
{
	if((_numerator == other.getNumerator()) && (_denominator == other.getDenominator()))
	{
		return true;
	}
	else
	{
		return false;
	}
}

/*
* Returns true if the Rationals are not equal and false otherwise
*/
const bool operator!=(const Rational& other) const
{
	// uses the == operator func
	if(*this == (other))
	{
		return false;
	}
	else
	{
		return true;
	}
}

/**
* Returns the multiplication of this Rational and other
* Multiplication should be calculated in the way we calculate
* multiplication of 2 ratios (separately for the numerator and for the
* denominator).
*/
const Rational operator*(const Rational& other) const
{
	long int tmpDenominator = getDenominator();
	long int tmpNumerator = getNumerator();
	
	// make the product
	tmpNumerator = tmpNumerator * other.getNumerator();
	tmpDenominator = tmpDenominator * other.getDenominator();
	
	// sets and return as Rational object
	Rational newRational = Rational(tmpNumerator, tmpDenominator);
	return newRational;
}

/**
* Returns the sum of this Rational and other
*/
const Rational operator+(const Rational& other) const
{
	long int tmpNumerator = other.getNumerator();
	long int tmpDenominator = other.getDenominator();
	
	// kefel behatzlava
	tmpNumerator = (tmpNumerator * getDenominator()) + (getNumerator() * tmpDenominator);
	
	// shared Denominator
	tmpDenominator = tmpDenominator * getDenominator();
	
	return Rational(tmpNumerator, tmpDenominator);
}

/**
* Returns a string of the rational number
*/
Rational toString() const
{
	return (std::to_string(_numerator) + HILUK + std::to_string(_denominator));
}

private:

long int _numerator;
long int _denominator;

static const char HILUK = '/';
static const long int defDenominator = 1;
static const long int ZEROVAL = 0;
static const long int NEGATIVE = -1;

// fix values for zero Rationals
void _fixZero()
{
	if (_numerator == ZEROVAL)
	{
		_denominator = defDenominator;
	}
}

// fix values for negative denominator
void _fixNegativity()
{
	if(_denominator < ZEROVAL)
	{
		_denominator = NEGATIVE * _denominator;
		_numerator = NEGATIVE * _numerator;
	}
}	

// shrink values of nominator and denominator to he lowest as can withput demageing the Rational value
void _makeRepresentationCoprime()
{
	// gets GCD and devides values
	long int GCD = std::abs(_greatestCommonDivisor(_numerator, _denominator));
	_numerator = _numerator / GCD;
	_denominator = _denominator / GCD;
}

// calc the GCD
static const long int _greatestCommonDivisor(const long int& a, const long int& b)
{
	long int aTmp = a;
	long int bTmp = b;
	while (bTmp != ZEROVAL)
	{
		long int nextGCD = aTmp % bTmp;
		aTmp = bTmp;
		bTmp = nextGCD;
	}
	return aTmp;
}

};